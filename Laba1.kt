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

fun  main (args:Array <String>)
{
    val scanner = Scanner(`in`)
	println("Input your name...")
    val name = scanner.next()
	println("Your favourite language...")
    val lang = scanner.next()
	println("Hello, $name")
	println(liar(lang))
}