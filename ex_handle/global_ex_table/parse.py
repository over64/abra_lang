#!/usr/bin/env python3
import sys, re
# import llipy

class Call:
    def __init__(self, name, debugId, brId):
        self.name = name
        self.debugId = debugId
        self.brId = brId

    def __repr__(self):
        return 'Call(name: %s, debugId: %s, brId: %s)' % (self.name, self.debugId, self.brId)

class Fn:
    def __init__(self, name, proto, calls):
        self.name = name
        self.proto = proto
        self.calls = calls

    def __repr__(self):
        return 'Fn(%s, %s)' % (self.name, self.calls)

class Location:
    def __init__(self, id, line):
        self.id = id
        self.line = line

class Ctx:
    def __init__(self):
        self.brId = 0
        self.functions = []
        self.locations = []

    def nextBr(self):
        self.brId += 1
        return self.brId


def parseCall(ctx, fn, call):
    head = call[call.index('@') + 1::]
    tail = head[:head.index('('):]
    name = tail.strip('"')

    dhead = call[call.index('!dbg') + 1::]
    dtail = dhead[dhead.index('!') + 1::]
    debugId = int(dtail)

    if not name.startswith('llvm.dbg'):
        brId = ctx.nextBr()
        fn.calls.append(Call(name, debugId, brId))

        print('call void asm sideeffect ".Leh_label%s:", ""()' % brId)
        print('call void asm sideeffect "movw %%ax, .Leh_label%s-%s", ""()' % (brId, fn.name))
        print('call void asm sideeffect ".short .Leh_label%s-%s", ""()' % (brId, fn.name))

        print('  br label %%eh_branch%d' % brId)
        print('eh_branch%d:' % brId)

def parseDebug(ctx, debug):
    pass

def parseFunction(ctx, f, header):
    head = header[header.index('@') + 1::]
    tail = head[:head.index('('):]
    proto = header.lstrip('define')
    proto = proto.lstrip(' dso_local' )
    proto = proto[:proto.index(')') + 1:]
    proto = re.sub('@[a-zA-z0-9]+', '', proto) + '*'
#    print(proto)
    fn = Fn(tail.strip('"'), proto, calls = [])

    ctx.functions.append(fn)

#    print('entry:')

    while True:
        line = f.readline()

        if not line:
            break

        print(line, end='')

        if '}' in line:
            return


        if line.startswith('define'):
            parseFunction(f, line)

        if ' call ' in line:
            parseCall(ctx, fn, line)

def writeMetadata(ctx):
    with open('tbl.ll', 'w') as f:
        f.write('%struct.eh_table = type { i16, i16 }\n')
        for fn in ctx.functions:
            if fn.calls:
                f.write('@tbl.%s = constant %%struct.eh_table {\n' % fn.name)
                f.write('  i16 trunc(\n')
                f.write('    i64 sub(\n')
                f.write('      i64 ptrtoint(i8* blockaddress(@%s, %%eh_branch%d) to i64),\n' % (fn.name, fn.calls[0].brId))
                f.write('      i64 ptrtoint(i8* bitcast(%s @%s to i8*) to i64)) to i16),\n' % (fn.proto, fn.name))
                f.write('  i16 0')
                f.write('}\n')


if __name__ == '__main__':
    ctx = Ctx()
    f = sys.stdin

    while True:
        line = f.readline()

        if not line:
            break

        print(line, end='')

        if line.startswith('define'):
            parseFunction(ctx, f, line)

        if line.startswith('!'):
            parseDebug(ctx, line)

    # print(ctx.__dict__)
    writeMetadata(ctx)