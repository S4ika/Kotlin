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
//Task4
tailrec fun ddigitDown(num:Int, acum : Int, f : (Int, Int) -> Int) : Int
        = if (num == 0) acum else ddigitDown(num / 10, f (num % 10, acum) , f)

fun dminDigitDown(num : Int) : Int = ddigitDown(num, 9, {a, b -> if (a < b) a else b})

fun dmaxDigitDown(num : Int) : Int = ddigitDown(num, 0, {a, b -> if (a > b) a else b})
//Task 5
tailrec fun digitIfDown(num:Int, acum : Int, f : (Int, Int) -> Int, pr : (Int) -> Boolean) : Int
        = if (num == 0) acum else digitIfDown(num / 10, if (pr(num % 10)) f (num % 10, acum) else acum, f, pr)

fun minDigitIfDown(num : Int) : Int = digitIfDown(num, 9, {a, b -> if (a < b) a else b}, {x -> x % 2 == 0})

fun maxDigitIfDown(num : Int) : Int = digitIfDown(num, 0, {a, b -> if (a > b) a else b}, {x -> x % 2 == 0})
//Task 6
fun test1(num : Int) : Int = digitIfDown(num, 1, {a,b -> a * b}, {x -> x % 2 == 0 && x > 6}) // считает произведение четных цифр >6
fun test2(num : Int) : Int = digitIfDown(num, 1, {a,b -> a + b}, {x -> x in 4..6 }) // считает сумму цифр больших 4 и меньших 7
fun test3(num : Int) : Int = digitIfDown(num, 1, {a,b -> a + b}, {x -> x == 1}) // считает сумму единиц в числе
//Task 7
// 7 Задание
//7.1
fun sumProst(num : Int) : Int = sumProstDel(num,0)

tailrec fun primeDigit(digit:Int):Int = if (digit < 2) 0 else prime(digit,2)

tailrec fun prime(digit:Int,iterator:Int):Int = if(digit == iterator) digit else if(digit%iterator != 0) prime(digit,iterator+1) else 0

tailrec fun sumProstDel(num:Int,sum:Int): Int = if (num == 0) sum else {
    if (primeDigit(num % 10) != 0) sumProstDel(num / 10,sum + primeDigit(num % 10))
    else sumProstDel(num / 10, sum)
}
//7.2
fun sumOddDigitGreaterThan3(num:Int): Int = sumOddDigit(num,0,3)
fun oddDigit(digit:Int): Boolean = digit%2 != 0
tailrec fun sumOddDigit(digit:Int,sum:Int,condition:Int):Int = if(digit==0) sum else{
    if (digit % 10 > condition && (oddDigit(digit%10)) ) sumOddDigit(digit/10,sum+1,condition)
    else sumOddDigit(digit/10,sum,condition)
}
//7.3
fun div(num:Int): Int = mulDel(num,1,1)
fun divisor(num:Int,del:Int):Boolean = num%del==0
tailrec fun mulDel(num:Int,del:Int,pr:Int): Int =
    when {
        num == del -> pr
        sumElem(del)<sumElem(num) && divisor(num,del)->
        {   println(del)
            mulDel(num,del+1,pr*del)
        }
        else -> mulDel(num,del+1,pr)
    }
	
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
	println("Min digit of a number(down) : ${dminDigitDown(s)}")
    println("Min digit of a number(down) : ${ minDigitIfDown(s)}")
	println("Tests results:\nTest 1: test1(s)\nTest 2: test2(s)\nTest 3: test3(s)")
}