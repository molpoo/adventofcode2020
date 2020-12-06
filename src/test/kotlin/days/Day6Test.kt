package days

import org.junit.Test
import kotlin.test.assertEquals

class Day6Test {

    private val day = Day6()
    private val input =
        """abc

            a
            b
            c
            
            ab
            ac
            
            a
            a
            a
            a
            
            b
            """.trimIndent().split('\n')

    @Test
    fun solvePart1Test() {
        assertEquals("11", day.solvePart1(input))
    }

    @Test
    fun solvePart2Test() {
        assertEquals("6", day.solvePart2(input))
    }
}