package days

import days.Day3
import org.junit.Test
import kotlin.test.assertEquals

class Day3Test {

    private val day = Day3()
    private val input = listOf(
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#",
    )

    @Test
    fun solvePart1Test() {
        assertEquals("7", day.solvePart1(input))
    }

    @Test
    fun solvePart2Test() {
        assertEquals("336", day.solvePart2(input))
    }
}