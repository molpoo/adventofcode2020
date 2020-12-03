import org.junit.Test
import kotlin.test.assertEquals

class Day2Text {

    private val day = Day2()
    private val input = listOf(
        "1-3 a: abcde",
        "1-3 b: cdefg",
        "2-9 c: ccccccccc"
    )

    @Test
    fun solvePart1Test() {
        assertEquals("2", day.solvePart1(input))
    }

    @Test
    fun solvePart2Test() {
        assertEquals("1", day.solvePart2(input))
    }
}