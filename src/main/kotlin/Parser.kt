import java.math.BigDecimal
import java.util.*

class Parser {
    private val input: List<String>
    init {
        Scanner(System.`in`)
        input = readLine()
            ?.split(" ")
            ?: throw IllegalArgumentException("Не смог распарсить пользовательский ввод")
    }

    fun detectNums(): List<BigDecimal> {
        return input.mapNotNull { it.toBigDecimalOrNull() }
    }

    fun detectSigns(): List<Char> {
        return input
            .mapNotNull { it.singleOrNull() }
            .filter { it == '+' || it == '-' || it == '*' || it == '/' }
    }
}