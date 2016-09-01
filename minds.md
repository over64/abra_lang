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

```
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
```

### Менеджмент ресурсов / обработка ошибок
1. Ввести тип-ресурс, который является value типом
2. Компилятор должен требовать вызов close на ресурсе в момент когда он исчезает из области видимости
3. После вызова close ресурс невозможно использовать (нельзя делать вызовы на объекте, делать return, копировать)
```
resource type Fd = llvm { i32 }
def close : (self: Fd) -> Unit = llvm {
}

def foo = {
}: Res
```

Идея: бывают случаи когда необходимо вызвать код (возможно чужой), который может упасть (выкинуть panic)
в этом случае необходим механизм перехвата паники и гарантированный откат программы до определенной точки с сохранением
согласованности программы. Под согласованностью понимается:
 1. Отсутствие утечек памяти
 2. Отсутствие утечек ресурсов
 3. Нельзя копировать?
 4. Thread тоже ресурс?
```
 def foo(): Int = {
 }
 type Res = Int | None = Unit

 val res =
     try foo()
     else { trace ->
        log(trace)
        None
     }
```
     
### Язык должен удовлетворять потребности разработчиков
Согаласно создателю языка Perl программист характеризуется следующими чертами характера:
- лень
- самомнение
- нетерпимость

язык должен делать таких людей счастливыми!
     
### Система модулей
1. При импорте модуля импортируются только типы
2. Имя модуля должно быть легко отличимо от имени типа?
3. В файле может быть несколько модулей?
4. Могут ли типы декларироваться в файлах а не модулях?
5. Состояние модуля
6. Нужен ли модуль для main?
7. Возможны ли импорты вне модуля?
8. Модули и генерики
```
import abra.lang._List<Int | Float>
import abra.lang._IO with Fd, Reader

val list = _List.mk(1, 2, 3)
list.drop(1).take(10).toList

val file: Fd = _IO.open('/tmp/file')
file.close()
```


```abra
# main.abra
import abra.lang.list
def main =
```
### Система модулей. Ver 1.0
1. Весь код хранится в файлах. Например int.abra
2. Иерархия импортов напрямую отображена на файловую систему
3. При импорте файла импортируются типы, описанные в файле
4. При конфликтующих именах типов необходимо разрешить конфликт вручную, 
используя where statement
```
import org.file1 # imports Type1, Type2
import org.file2 where Type1 = File1Type1, Type2 = File1Type2
```

```abra
# main.abra
import org.postgresql.pg

type User = (name: String, age: Int, lastName: Option[String]= None)

def main = {
    val (uname, passwd) = ('over', '123')
    val pool = pg.mkPool('localhost:5432/test_db', uname, passwd, connections=1)
    
    # query for users    
    (1 to 10).foreach({ id ->    
        # direct entity mapping using macro?
        val user = pool
            .execute[User]('select #name, #age from users where id = $id').head
        user.print
    })
    
    pool.close
}: Unit
```

Generics?

```
# org/examples/summator.abra
# examples for generics

# fetch multiply add instruction
require T with {
    def +: (o1: T, o2: T) -> T
}
def fma = { a: T, b: T, c: T ->
    a * b + c
}: T

fma(1, 2, 3)

```
```abra
# abra/lists.abra
require T
type List = (head: T, tail: Option<List>)
```
```
require T, U
type Option = T | None
def map<T, U> = { o1: Option<T>, f: (o1: Option<T>) -> U ->

}: Option<U>
```