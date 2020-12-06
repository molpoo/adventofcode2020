package days

import org.junit.Test
import kotlin.test.assertEquals

class Day5Test {

    private val day = Day5()

    @Test
    fun solvePart1Test() {
        assertEquals("357", day.solvePart1(listOf("FBFBBFFRLR")))
        assertEquals("567", day.solvePart1(listOf("BFFFBBFRRR")))
        assertEquals("119", day.solvePart1(listOf("FFFBBBFRRR")))
        assertEquals("820", day.solvePart1(listOf("BBFFBBFRLL")))
    }

    @Test
    fun solvePart2Test() {
        assertEquals("579", day.solvePart2(day.input))
    }
}