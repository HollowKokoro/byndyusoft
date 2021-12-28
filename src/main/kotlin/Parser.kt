import java.math.BigDecimal
import java.util.*

class Parser {
    private val input: String
    init {
        Scanner(System.`in`)
        input = readLine() ?: throw IllegalArgumentException("Не удалось распарсить пользовательский ввод")
    }

    fun detectSigns(): List<Char> {
        val signs = Sign.values().map { it.sign }.toMutableList()
        signs.removeIf { !input.contains(it) }
        return signs
    }

    fun detectNums(): List<BigDecimal> {
        return input.split(
            Sign.PLUS.sign,
            Sign.MINUS.sign,
            Sign.MULTIPLY.sign,
            Sign.DIVIDE.sign
        ).mapNotNull { it.toBigDecimalOrNull() }
    }
}