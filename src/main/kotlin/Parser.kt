import java.math.BigDecimal
import java.util.*
import kotlin.math.max
import kotlin.math.min

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

    fun extractIndexOfInput(signs: MutableList<Char>, sign1: Char, sign2: Char): Int {
        val sign1Index = signs.indexOf(sign1)
        val sign2Index = signs.indexOf(sign2)
        return if (sign1Index != -1 && sign2Index != -1) {
            min(sign1Index, sign2Index)
        } else {
            max(sign1Index, sign2Index)
        }
    }
}
