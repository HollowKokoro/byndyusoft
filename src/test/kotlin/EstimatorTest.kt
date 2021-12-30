
import kotlin.test.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class EstimatorTest {
    @Mock
    private lateinit var parser: Parser

    @BeforeTest
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(parser.detectNums()).thenReturn(mutableListOf(BigDecimal(2), BigDecimal(2), BigDecimal(2)))
        whenever(parser.detectSigns()).thenReturn(mutableListOf('*', '+'))
    }

    @Test
    fun countOperationTest() {
        val signs = parser.detectSigns().toMutableList()
        val nums = parser.detectNums().toMutableList()
        val sign1 = '+'
        val sign2 = '*'
        while (signs.contains(sign1) || signs.contains(sign2)) {
            val indexByPriority = parser.extractIndexOfInput(signs, sign1, sign2)
            when (signs[indexByPriority]) {
                Sign.MULTIPLY.sign -> nums[indexByPriority] *= nums[indexByPriority + 1]
                Sign.DIVIDE.sign -> nums[indexByPriority] /= nums[indexByPriority + 1]
                Sign.PLUS.sign -> nums[indexByPriority] += nums[indexByPriority + 1]
                Sign.MINUS.sign -> nums[indexByPriority] -= nums[indexByPriority + 1]
            }
            signs.removeAt(indexByPriority)
            nums.removeAt(indexByPriority + 1)
        }
        val expected = 6
        assertEquals(expected.toDouble(), nums[0].toDouble())
    }
}
