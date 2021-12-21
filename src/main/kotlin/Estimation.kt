import java.math.BigDecimal
import kotlin.math.max
import kotlin.math.min

class Estimation {
    private val parser = Parser()
    private val signs = parser.detectSigns().toMutableList()
    private val nums = parser.detectNums().toMutableList()
    private var flag = true

    fun estimate(): MutableList<BigDecimal> {
        for (i in signs.size downTo 1) {
            count('*', '/')
            count('+', '-')
            if (flag) {
                throw IllegalArgumentException("Не смог распарсить пользовательский ввод")
            }
        }
        return nums
    }

    private fun count(sign1: Char, sign2: Char) {
        while (signs.contains(sign1) || signs.contains(sign2)) {
            val sign1Index = signs.indexOf(sign1)
            val sign2Index = signs.indexOf(sign2)
            val priority = if (sign1Index != -1 && sign2Index != -1) {
                min(sign1Index, sign2Index)
            } else {
                max(sign1Index, sign2Index)
            }
            when (signs[priority]) {
                '*' -> nums[priority] *= nums[priority + 1]
                '/' -> nums[priority] /= nums[priority + 1]
                '+' -> nums[priority] += nums[priority + 1]
                '-' -> nums[priority] -= nums[priority + 1]
            }
            signs.removeAt(priority)
            nums.removeAt(priority + 1)
            flag = false
        }
    }
}