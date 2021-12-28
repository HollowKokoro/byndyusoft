import java.math.BigDecimal
import kotlin.math.max
import kotlin.math.min

class Estimator {
    private val parser = Parser()
    private val signs = parser.detectSigns().toMutableList()
    private val nums = parser.detectNums().toMutableList()

    fun countAllOperations(): MutableList<BigDecimal> {
        if (signs.size == 0 || nums.size < 2) {
            throw IllegalArgumentException("Не удалось распарсить пользовательский ввод")
        }
        for (operation in signs.size downTo 1) {
            countOperation(Sign.MULTIPLY.sign, Sign.DIVIDE.sign)
            countOperation(Sign.PLUS.sign, Sign.MINUS.sign)
        }
        return nums
    }

    private fun countOperation(sign1: Char, sign2: Char) {
        while (signs.contains(sign1) || signs.contains(sign2)) {
            val indexByPriority = extractIndexOfInput(sign1, sign2)
            when (signs[indexByPriority]) {
                Sign.MULTIPLY.sign -> nums[indexByPriority] *= nums[indexByPriority + 1]
                Sign.DIVIDE.sign -> nums[indexByPriority] /= nums[indexByPriority + 1]
                Sign.PLUS.sign -> nums[indexByPriority] += nums[indexByPriority + 1]
                Sign.MINUS.sign -> nums[indexByPriority] -= nums[indexByPriority + 1]
            }
            signs.removeAt(indexByPriority)
            nums.removeAt(indexByPriority + 1)
        }
    }

    private fun extractIndexOfInput(sign1: Char, sign2: Char): Int {
        val sign1Index = signs.indexOf(sign1)
        val sign2Index = signs.indexOf(sign2)
        return if (sign1Index != -1 && sign2Index != -1) {
            min(sign1Index, sign2Index)
        } else {
            max(sign1Index, sign2Index)
        }
    }
}