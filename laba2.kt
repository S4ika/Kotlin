import java.lang.System.`in`
import java.util.*
//Task 1
fun sumElem(num:Int) : Int = if (num == 0) 0 else (num%10) + sumElem(num/10)
//Task 2
fun sumDown(s:Int):Int = sumElemDown(s,0)
tailrec fun sumElemDown(num:Int,sum:Int):Int = if (num > 0) sumElemDown(num / 10,(sum + num % 10)) else sum

fun main()
{
    val scanner = Scanner(`in`)
    var s:Int = scanner.nextInt()
    println("Sum of digits of a number : ${sumElem(s)}")
	println("Sum of digits of a number : ${sumDown(s)}")
}