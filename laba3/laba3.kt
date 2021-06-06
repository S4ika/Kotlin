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
	
//1.28 Дан
//целочисленный
//массив.
//Необходимо
//найти
//элементы,
//расположенные между первым и последним максимальным.
fun findElemsBetweenHighs(array: Array<Int>) =
    if(array.elementAt(maxArrayEl(array)) < array.elementAt(secondMax(array)))
        prntElsBtwnTwoMax(array,array.elementAt(maxArrayEl(array)),array.elementAt(secondMax(array)),array.elementAt(maxArrayEl(array))+1)
    else
        prntElsBtwnTwoMax(array,array.elementAt(secondMax(array)),array.elementAt(maxArrayEl(array)),array.elementAt(secondMax(array)+1))

fun prntElsBtwnTwoMax(array:Array<Int>,indexFirstMax:Int,indexSecondMax:Int,counter:Int) {
    if (counter == indexSecondMax) println(array[counter])
    else {
        println(array[counter])
        prntElsBtwnTwoMax(array, indexFirstMax, indexSecondMax, counter + 1)
    }
}

fun secondMax (array:Array<Int>) = secondMax(array,array.elementAt(maxArrayEl(array)),0,array[0])
fun secondMax(array:Array<Int>,indexMax: Int,counter:Int, secondMax:Int):Int =
    if(array.size == counter) secondMax
    else secondMax(array,indexMax,counter + 1,if(counter != indexMax && array[counter]>secondMax) array[counter] else secondMax)

//1.37 Дан целочисленный массив. Вывести индексы элементов, которые
//меньше своего левого соседа, и количество таких чисел.
fun countIndLitlLeftNeigh(array:Array<Int>) = countIndLitlLeftNeigh(array,0, 0)
fun countIndLitlLeftNeigh(array:Array<Int>,counter:Int, kolvo:Int):Int =
    if(counter == array.size) kolvo
    else if (counter > 0 && array[counter] > array[counter-1])
    {
        println(counter)
        countIndLitlLeftNeigh(array,counter+1,kolvo+1)
    }
    else countIndLitlLeftNeigh(array,counter+1,kolvo)
	
//1.43 Дан
//целочисленный
//массив.
//Необходимо
//найти
//количество
//минимальных элементов.
fun kolvoMinElms(array:Array<Int>) = kolvoMinElms(array,0,minArrayEl(array),0)
fun kolvoMinElms(array:Array<Int>,counter:Int,minEl:Int,kolvo:Int):Int =
    if (array.size == counter) kolvo
    else kolvoMinElms(array,counter+1,minEl,if(array[counter]==minEl) kolvo+1 else kolvo)
	
//5.1
fun listOp(): List<Int> {
    print("введите размер списка:  ")
    val size = readLine()!!.toInt()
    val l = mutableListOf<Int>()
    return listInput(l, size)
}

fun listInput(l : List<Int>, size: Int) : List<Int> =   // ввод массива с клавиатуры
    listInput(l, 0, size)
tailrec fun listInput(l : List<Int>, counter : Int, size : Int) : List<Int> =
    if (counter == size) l else listInput(l.plus(readLine()!!.toInt()), counter + 1, size)


tailrec fun listOp(a: Iterator<Int>, f: (Int, Int) -> Int, result: Int): Int =
    if (a.iterator().hasNext() == false) result else
        listOp(a, f, f(a.iterator().next(),result))

//Ввод из файла
fun listInputFile(input : Map<Int, Int>) : MutableList<Int> {
    val numbers:MutableList<Int> = MutableList(input.size){0}
    return listInputFile(numbers, 0,  input)
}

fun listInputFile(list : MutableList<Int>, counter : Int, input : Map<Int, Int>) : MutableList<Int> =
    if (counter == list.size) list
    else {
        list[counter] = input[counter]!!
        listInputFile(list, counter + 1, input)
    }

//Организация чтения из файла
fun listInputFile(fileName:String) : MutableList<Int> {
    val input = File(fileName).readLines()
        .withIndex()
        .map{ indexedValue -> indexedValue.index to indexedValue.value.toInt() }//создает список индексов и строк соответсвующих
        .toMap()

    return listInputFile(input)
}

//8.1 Дан
//целочисленный
//массив.
//Необходимо
//найти
//количество
//элементов, расположенных после последнего максимального.
fun sumElAfterMax(list:MutableList<Int>) = findLastMax(list,list[0],0,0)
fun findLastMax(list:MutableList<Int>,max: Int,counter: Int, kolvo: Int):Int =
    if (counter == list.size) kolvo
    else if(list[counter] > max) findLastMax(list, list[counter],counter + 1, 0)
    else findLastMax(list,max,counter + 1, kolvo + 1)
//8.15 Дан целочисленный массив и натуральный индекс (число, меньшее
//размера массива). Необходимо определить является ли элемент по
//указанному индексу локальным минимумом.
fun localMin(list:MutableList<Int>,counter: Int):Boolean =
    when (counter)
    {
        1 -> list[counter] < list[counter + 1]
        list.size - 1 -> list[counter] < list[counter - 1]
        else -> (list[counter] < list[counter - 1] && list[counter]  < list[counter + 1])
    }
//8.25 Дан целочисленный массив и интервал a..b. Необходимо найти
//максимальный из элементов в этом интервале.
fun localMaxInRange(list:MutableList<Int>) = localMaxInRange(list,2,2,5,list[2])
fun localMaxInRange(list:MutableList<Int>, counter:Int, a:Int, b:Int, max: Int): Int =
    if(counter > b) max
    else localMaxInRange(list,counter + 1,a,b,(if(list[counter] > max) list[counter] else max))
//8.37 Дан целочисленный массив. Вывести индексы элементов, которые
//меньше своего левого соседа, и количество таких чисел.
fun countIndLitlLeftNeigh(array:MutableList<Int>) = countIndLitlLeftNeigh(array,0, 0)
fun countIndLitlLeftNeigh(array:MutableList<Int>,counter:Int, kolvo:Int):Int =
    if(counter == array.size) kolvo
    else if (counter > 0 && array[counter] > array[counter-1])
    {
        println(counter)
        countIndLitlLeftNeigh(array,counter+1,kolvo+1)
    }
    else countIndLitlLeftNeigh(array,counter+1,kolvo)
//8.43 Дан
//целочисленный
//массив.
//Необходимо
//найти
//количество
//минимальных элементов.
fun kolvoMinElms(array:MutableList<Int>) = kolvoMinElms(array,0,minListEl(array),0)
fun kolvoMinElms(array:MutableList<Int>,counter:Int,minEl:Int,kolvo:Int):Int =
    if (array.size == counter) kolvo
    else kolvoMinElms(array,counter+1,minEl,if(array[counter]==minEl) kolvo+1 else kolvo)

8.49//Для введенного списка положительных чисел построить список всех
//положительных простых делителей элементов списка без повторений.
tailrec fun listOfPrimeDels(list: List<Int>, listDel: MutableList<Int>, counter: Int):List<Int> = if(counter != list.size)
    listOfPrimeDels(list, listOfDel(list[counter],1, listDel), counter + 1) else listDel.distinct()

//Формирование списка делителей числа
fun listOfDel(num:Int,del:Int, list: MutableList<Int>):MutableList<Int> = if(del == num) list.plus(del).toMutableList()
else if(num % del == 0 && primeDigit(del)) listOfDel(num, del + 1, list.plus(del).toMutableList())
else listOfDel(num, del + 1, list)
//Проверка числа на простоту
fun primeDigit(num:Int) = primeDigit(num,2)
fun primeDigit(num:Int,del:Int):Boolean =
    if (num == del) true
    else if (num%del == 0) false
    else primeDigit(num,del+1)
	