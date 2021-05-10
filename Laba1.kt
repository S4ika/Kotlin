import java.lang.System.`in`
import java.util.*

fun liar(lang:String):String =
    when(lang)
    {
        "Kotlin","Prolog" -> "Sycophant"
        else -> "Good choice"
    } 
	
fun sumElem(num:Int) : Int = if(num == 0) 0 else (num % 10)+ sumElem(num / 10)

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