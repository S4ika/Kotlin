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

