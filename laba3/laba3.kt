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
