package days

import days.Day1
import org.junit.Test
import kotlin.test.assertEquals

class Day1Test {

    private val day = Day1()
    private val input = listOf(
        "1721",
        "979",
        "366",
        "299",
        "675",
        "1456"
    )

    @Test
    fun solvePart1Test() {
        assertEquals("514579", day.solvePart1(input))
    }

    @Test
    fun solvePart2Test() {
        assertEquals("241861950", day.solvePart2(input))
    }
}