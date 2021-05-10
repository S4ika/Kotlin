import java.lang.System.`in`
import java.util.*

fun liar(lang:String):String =
    when(lang)
    {
        "Kotlin","Prolog" -> "Sycophant"
        else -> "Good choice"
    } 
	
fun sumElem(num:Int) : Int = if(num == 0) 0 else (num % 10)+ sumElem(num / 10)

//Произведение элементов
fun mulElem(num:Int) : Int = if( num == 0) 1 else (num%10) * mulElem(num/10)
//Максимальная цифра
fun max(num:Int) : Int = maxElem(num,0)
fun maxElem(num:Int,max:Int) : Int = if (num == 0) max else if (num%10 > max) maxElem(num/10,num%10) else maxElem(num/10,max)
//Минимальное число
fun min(num:Int) : Int = minElem(num,9)
fun minElem(num:Int,min:Int) : Int = if (num == 0) min else if (num%10 < min) minElem(num/10,num%10) else minElem(num/10,min)

//Прoстое число
fun primeDigit(digit:Int):Int = if (digit < 2) 0 else prime(digit,2)

fun prime(digit:Int,iterator:Int):Int = if(digit == iterator) digit else if(digit%iterator != 0) prime(digit,iterator+1) else 0

fun sumPrimeDiv(digit:Int):Int = if (digit == 0) 0 else (primeDigit(digit%10)+sumPrimeDiv(digit/10))

//Найти количество нечетных цифр числа, больших 3
fun sumOddDigitGreaterThen3(num:Int): Int = sumOddDigit(num,0,3)
fun oddDigit(digit:Int): Boolean = digit%2 != 0
fun sumOddDigit(digit:Int,sum:Int,condition:Int):Int = if(digit==0) sum else{
    if (digit % 10 > condition && (oddDigit(digit%10)) ) sumOddDigit(digit/10,sum+1,condition)
    else sumOddDigit(digit/10,sum,condition)
}

//Найти прозведение таких делителей числа, сумма цифр которых меньше, чем сумма цифр исходного числа.
fun div(num:Int): Int = mulDel(num,1,1)
fun divisor(num:Int,del:Int):Boolean = num%del==0
fun mulDel(num:Int,del:Int,pr:Int): Int =
    when {
        num == del -> pr
        sumElem(del)<sumElem(num) && divisor(num,del)->
        {   println(del)
            mulDel(num,del+1,pr*del)
        }
        else -> mulDel(num,del+1,pr)
    }

fun largestProd(mtx:Array<Array<Int>>): Int
{
    var maxProd = 0
    var fN = 0
    var sN = 0
    var tN = 0
    var foN = 0
    for (i in 0..19)
        for (j in 0..16)
            if(mtx[i][j] * mtx[i][j+1] * mtx[i][j+2] * mtx [i][j+3] > maxProd) {
                fN = mtx[i][j]
                sN = mtx[i][j + 1]
                tN = mtx[i][j + 2]
                foN = mtx[i][j + 3]
                maxProd = mtx[i][j] * mtx[i][j + 1] * mtx[i][j + 2] * mtx[i][j + 3]
            }
    for (i in 0..16)
        for (j in 0..19)
            if(mtx[i][j] * mtx[i+1][j] * mtx[i+2][j] * mtx [i+3][j] > maxProd) {
                fN = mtx[i][j]
                sN = mtx[i + 1][j]
                tN = mtx[i + 2][j]
                foN = mtx[i + 3][j]
                maxProd = mtx[i][j] * mtx[i + 1][j] * mtx[i + 2][j] * mtx[i + 3][j]
            }
    for (i in 0..16)
        for (j in 0..16)
            if(mtx[i][j] * mtx[i+1][j+1] * mtx[i+2][j+2] * mtx[i+3][j+3] > maxProd)
            {   fN = mtx[i][j]
                sN = mtx[i + 1][j + 1]
                tN = mtx[i + 2][j + 2]
                foN = mtx[i + 3][j + 3]
                maxProd = mtx[i][j] * mtx[i+1][j+1] * mtx[i+2][j+2] * mtx[i+3][j+3]
            }
    for (i in 3..19)
        for (j in 0..16)
            if (mtx[i][j] * mtx[i-1][j+1] * mtx[i-2][j+2] * mtx[i-3][j+3] > maxProd) {
                fN = mtx[i][j]
                sN = mtx[i - 1][j + 1]
                tN = mtx[i - 2][j + 2]
                foN = mtx[i - 3][j + 3]
                maxProd = mtx[i][j] * mtx[i - 1][j + 1] * mtx[i - 2][j + 2] * mtx[i - 3][j + 3]
            }
    println("$fN\n$sN\n$tN\n$foN\n")
    return maxProd
}

fun  main (args:Array <String>)
{
    val scanner = Scanner(`in`)
	println("Input your name...")
    val name = scanner.next()
	println("Your favourite language...")
    val lang = scanner.next()
	println("Hello, $name")
	println(liar(lang))
	do
    {
        println("Choose a method\n1.Sum of elms\n2.Mul of elms\n3.Max elem\n4.Min elem")
        println("5.Sum of odd elms greater than 3\n6.Sum of prime digits of a number")
        println("7.Mul of div whose sun of digits is greater than num\n0.Exit")
        val vibor = scanner.next()
        println("Input number...")
        val num = scanner.nextInt()
        when(vibor)
        {
            "1" -> println("Sum of elms = ${sumElem(num)}")
            "2" -> println("Mul of elms = ${mulElem(num)}")
            "3" -> println("Max elem = ${max(num)}")
            "4" -> println("Min elem = ${min(num)}")
            "5" -> println("Sum of odd elms greater than 3 = ${sumOddDigitGreaterThen3(num)}")
            "6" -> println("Sum of prime digits of a number = "+sumPrimeDiv(num))
            "7" -> println("Mul of div whose sun of digits is greater than num = ${div(num)}")
        }
    }while(vibor!="0")
}