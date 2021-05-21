import java.lang.System.`in`
import java.util.*
//Task 1
fun sumElem(num:Int) : Int = if (num == 0) 0 else (num%10) + sumElem(num/10)

fun main()
{
    val scanner = Scanner(`in`)
    var s:Int = scanner.nextInt()
    println("Sum of digits of a number : ${sumElem(s)}")
}