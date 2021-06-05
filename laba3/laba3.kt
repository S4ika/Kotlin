import java.io.BufferedReader
import java.io.File
import java.lang.System.`in`
import java.nio.file.Files
import java.util.*
import java.nio.file.Paths

//Задание 1
tailrec fun arrayOp(array: Iterator<Int>, result : Int, lmd: (Int, Int) -> (Int)):Int =
    if (array.iterator().hasNext() == false) result
    else arrayOp(array,lmd(array.iterator().next(),result),lmd)

tailrec fun spuskPoMassivu(array:Array<Int>, acum: Int, lmd:(Int,Int) -> (Int),counter: Int): Int =
    if(counter == array.size) acum else spuskPoMassivu(array,lmd(acum,array[counter]),lmd,counter+1)

tailrec fun spuskPoMassivuMinMax(array:Array<Int>, acum: Int, lmd:(Int,Int) -> (Boolean),counter: Int): Int =
    if(counter == array.size) acum
    else spuskPoMassivuMinMax(array,if (lmd(array[counter],acum)) array[counter] else acum, lmd,counter+1)

fun sumArrayEl(array:Array<Int>) = spuskPoMassivu(array,0,{a,b -> a + b},0)
fun mulArrayEl(array:Array<Int>) = spuskPoMassivu(array,1,{a,b -> a * b},0)
fun maxArrayEl(array:Array<Int>) = spuskPoMassivuMinMax(array,array[0],{a,b -> a > b}, 0 )
fun minArrayEl(array:Array<Int>) = spuskPoMassivuMinMax(array,array[0],{a,b -> a < b}, 0 )

fun arrayIn():Array<Int>
{
    println("Введите размер массива")
    val scanner = Scanner(`in`)
    val size = scanner.nextInt()

    val array:Array<Int> = Array(size){0}
    for(i in 0..size-1) {
        println("Введите $i элемент массива : ")
        array[i] = scanner.nextInt()
    }
    return array
}
//task 3
//Ввод из файла
fun arrayInputFile(input : Map<Int, Int>) : Array<Int> {
    val numbers:Array<Int> = Array(input.size){0}
    return arrayInputFile(numbers, 0,  input)
}

fun arrayInputFile(array : Array<Int>, counter : Int, input : Map<Int, Int>) : Array<Int> =
    if (counter == array.size) array
    else {
        array[counter] = input[counter]!!
        arrayInputFile(array, counter + 1, input)
    }

//Организация чтения из файла
fun inputFile(fileName:String) : Array<Int> {
    val input = File(fileName).readLines()
        .withIndex()
        .map{ indexedValue -> indexedValue.index to indexedValue.value.toInt() }//создает список индексов и строк соответсвующих
        .toMap()

    return arrayInputFile(input)
}

//Функция выбора источника считывания(Клавиатура или файл)
fun selectInput() : Array<Int>{
    println("Откуда считывать массив?\n" +
            "Клавиатура\n" +
            "Файл")
    val scanner = Scanner(`in`)
    do {

        when (scanner.next()) {
            "Файл" -> {
                println("Введите имя файла: ")
                return inputFile(scanner.next())
            }
            "Клавиатура" -> return readArray()
            else -> { println("Введите заново") }
        }

    }while(true)
}
//1.1 Дан
//целочисленный
//массив.
//Необходимо
//найти
//количество
//элементов, расположенных после последнего максимального.
fun sumElAfterMax(array:Array<Int>) = findLastMax(array,array[0],0,0)
fun findLastMax(array:Array<Int>,max: Int,counter: Int, kolvo: Int):Int =
    if (counter == array.size) kolvo
    else if(array[counter] > max) findLastMax(array, array[counter],counter + 1, 0)
    else findLastMax(array,max,counter + 1, kolvo + 1)
//1.2 Дан
//целочисленный
//массив.
//Необходимо
//найти
//индекс
//минимального элемента.
fun findIndexMinEl(array:Array<Int>):Int = array.elementAt(minArrayEl(array))
//1.13 Дан целочисленный массив. Необходимо разместить элементы,
//расположенные до минимального, в конце массива.
fun elemsUntilMin(array:Array<Int>): MutableList<Int>
    {
        val list1: MutableList<Int> = mutableListOf<Int>()
        val list2: MutableList<Int> = mutableListOf<Int>()
        /*val list3: MutableList<Int> = addElmsAfterMinlist(list2,array,array.elementAt(minArrayEl(array)))
        val list4: MutableList<Int> = */
        return addElmsAfterMinlist(list2,array,(array.elementAt(minArrayEl(array))))// + addElmsUntilMin(list1,array,minArrayEl(array),0)) as MutableList<Int>
    }
tailrec fun addElmsUntilMin(list:MutableList<Int>,array:Array<Int>,min:Int,counter: Int):MutableList<Int> =
    if(array[counter] == min) list
    else {
        list.add(array[counter],list.size)
        addElmsUntilMin(list,array,min,counter+1)
    }
tailrec fun addElmsAfterMinlist(list:MutableList<Int>,array:Array<Int>,counter: Int):MutableList<Int> =
if(counter == array.size) list
else {
    list.add(array[counter],list.size)
    addElmsAfterMinlist(list,array,counter+1)
}
//1.15 Дан целочисленный массив и натуральный индекс (число, меньшее
//размера массива). Необходимо определить является ли элемент по
//указанному индексу локальным минимумом.
fun localMin(array:Array<Int>,counter: Int):Boolean =
    when (counter)
    {
        1 -> array[counter] < array[counter + 1]
        array.size - 1 -> array[counter] < array[counter - 1]
        else -> (array[counter] < array[counter - 1] && array[counter]  < array[counter + 1])
    }
//1.25 Дан целочисленный массив и интервал a..b. Необходимо найти
//максимальный из элементов в этом интервале.
fun localMaxInRange(array:Array<Int>) = localMaxInRange(array,2,2,5,array[2])
fun localMaxInRange(array:Array<Int>, counter:Int, a:Int, b:Int, max: Int): Int =
    if(counter > b) max
    else localMaxInRange(array,counter + 1,a,b,(if(array[counter] > max) array[counter] else max))