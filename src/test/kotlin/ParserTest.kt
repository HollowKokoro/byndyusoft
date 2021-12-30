import kotlin.math.max
import kotlin.math.min
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ParserTest {
    private val input = "2+2*2"

    @Test
    fun detectSigns() {
        val signs = Sign.values().map { it.sign }.toMutableList()
        signs.removeIf { !input.contains(it) }
        val expected = charArrayOf('+', '*')
        val actual = signs.toCharArray()
        assertContentEquals(expected, actual)
    }

    @Test
    fun detectNums() {
        val nums = input.split(
            Sign.PLUS.sign,
            Sign.MINUS.sign,
            Sign.MULTIPLY.sign,
            Sign.DIVIDE.sign
        ).mapNotNull { it.toBigDecimalOrNull() }
        val actual = nums.map { it.toInt() }.toIntArray()
        val expected = intArrayOf(2, 2, 2)
        assertContentEquals(expected, actual)
    }

    @Test
    fun extractIndexOfInput() {
        val signs: MutableList<Char> = mutableListOf('+', '*')
        val sign1 = '+'
        val sign2 = '*'
        val sign1Index = signs.indexOf(sign1)
        val sign2Index = signs.indexOf(sign2)
        val actual = if (sign1Index != -1 && sign2Index != -1) {
            min(sign1Index, sign2Index)
        } else {
            max(sign1Index, sign2Index)
        }
        val expected = 0
        assertEquals(expected.toDouble(), actual.toDouble())
    }
}
