package days

import Color.Companion.blue
import Color.Companion.purple
import Color.Companion.red
import Color.Companion.white
import kotlin.system.measureTimeMillis

abstract class Day(private val day: Int) {

    private val input by lazy {
        Day::class.java.getResource("/day$day.txt").readText().split("\n")
    }

    abstract fun solvePart1(input: List<String>): String

    abstract fun solvePart2(input: List<String>): String

    fun solve() {
        println("\n${red}<days.Day $day>$white")
        var result : String

        val executionTimePartOne = measureTimeMillis {
            result = solvePart1(input)
        }
        println("Part One: $blue$result$white [$purple$executionTimePartOne ms$white]")

        val executionTimePartTwo: Long = measureTimeMillis {
            result = solvePart2(input)
        }
        println("Part Two: $blue$result$white [$purple$executionTimePartTwo ms$white]")
    }

}