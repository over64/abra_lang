Meta programming via compiler plugins - не так как в RUST!!!1
Примеры плагинов:
1) Интерполяция строк. Типа sql"" в Scala только 'select * from table where id = $id'.sql
2) Concurrency ?
3) Сериализация, десериализация
4) evalOnCompile - реализация вычислений во время компиляции.
    Удобно для встраивания ресурсов в бинарь. Например, картиночек и тп.
    Warn - результат только immutable данные!

Многопоточность в языке - Fork Auto Join Pool
val a: Int fork= callSomeFunctionAsync()
a + 1 // compiler performs join implicitly
1) Simple tasks
2) Forkable tasks - tasks with stack
3) Work Stealing ForkJoinPool
4) Load / Store для публикации результатов
5) fork= против простых функций типа
    fork(expression), cancel(var_ident), timeout(var_ident, n), join(var_ident), select(var_ident*, handler*)
6) желательно чтобы в рантайме было поменьше магии

Generic Size Params
type FixedString<n1> = Char[n1]

module StaticHash<K, V> = { hash: (key: K) -> Int ->
    self type KeyValue = (key: K, value: V)
    type StaticHash<n1> = KeyValue[n1]

    def apply = \self: StaticHash<n1>, key: K -> self[key.hash mod n1].value

    def forEach = \self: StaticHash<n1>, fn: (key: K, value: V) -> Unit ->
        self.forEach(fn)
}

def main = {
    import FixedString
    import StaticHash<Int, FixedString<15>> (hash = \i -> i)

    val hm : StaticHash<3> = (
        (1, "Hello,"),
        (2, "dear"),
        (3, "%username%")
    )

    hm(2)
    hm.forEach { k, v -> println("$k -> $v") }
}

### Менеджмент ресурсов / обработка ошибок
1. Ввести тип-ресурс, который является value типом
2. Компилятор должен требовать вызов close на ресурсе в момент когда он исчезает из области видимости
3. После вызова close ресурс невозможно использовать (нельзя делать вызовы на объекте, делать return, копировать)

resource type Fd = llvm { i32 }
def close : (self: Fd) -> Unit = llvm {
}

def foo = {
}: Res

Идея: бывают случаи когда необходимо вызвать код (возможно чужой), который может упасть (выкинуть panic)
в этом случае необходим механизм перехвата паники и гарантированный откат программы до определенной точки с сохранением
согласованности программы. Под согласованностью понимается:
 1. Отсутствие утечек памяти
 2. Отсутствие утечек ресурсов
 3. Нельзя копировать?
 4. Thread тоже ресурс?

 def foo(): Int = {
 }
 type Res = Int | None = Unit

 val res =
     try foo()
     else { trace ->
        log(trace)
        None
     }