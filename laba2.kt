import java.lang.System.`in`
import java.util.*
//Task 1
fun sumElem(num:Int) : Int = if (num == 0) 0 else (num%10) + sumElem(num/10)
//Task 2
fun sumDown(s:Int):Int = sumElemDown(s,0)
tailrec fun sumElemDown(num:Int,sum:Int):Int = if (num > 0) sumElemDown(num / 10,(sum + num % 10)) else sum
//Task 3
fun mulDigitUp(num:Int):Int = if (num == 0) 1 else mulDigitUp(num / 10) * (num % 10)

fun mulDown(num:Int):Int = mulDigitDown(num,1)
tailrec fun mulDigitDown(num:Int,pr:Int):Int = if (num > 0 ) mulDigitDown(num / 10,(pr * (num % 10))) else pr

fun minDigit(num1:Int,num2:Int):Int = if(num1 > num2) num2 else num1
fun minDigitUp(num:Int):Int = if(num == 0) 0 else minDigit(minDigitUp(num / 10),(num % 10))

tailrec fun digitDown(num:Int, acum : Int, f : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int
    = if (num == 0) acum else digitDown(num / 10, if (pr(num % 10)) f (num % 10, acum) else acum, f, pr)

fun minDigitDown(num : Int) : Int = digitDown(num, 9, {a, b -> if (a < b) a else b}, {x -> true})

fun maxDigitDown(num : Int) : Int = digitDown(num, 0, {a, b -> if (a > b) a else b}, {x -> true})

fun main()
{
    val scanner = Scanner(`in`)
    var s:Int = scanner.nextInt()
    println("Sum of digits of a number : ${sumElem(s)}")
	println("Sum of digits of a number : ${sumDown(s)}")
	println("Mul of digits of a number : ${mulDigitUp(s)}")
    println("Mul of digits of a number (down) : ${mulDown(s)}")
    println("Min digit of a number : ${minDigitDown(s)}")
    println("Max digit of a number : ${maxDigitDown(s)}")
}