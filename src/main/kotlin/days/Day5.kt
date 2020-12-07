package days

import java.lang.IllegalArgumentException

class Day5 : Day(5, "Binary Boarding") {

    private fun upperHalf(range: IntRange): IntRange =
        range.first..range.last - ((range.last - range.first) / 2)

    private fun lowerHalf(range: IntRange): IntRange =
        range.last - (range.last - range.first) / 2..range.last

    private fun getSeatIds(input: List<String>): List<Int> {
        return input.flatMap { line ->
            val rowData = line.slice(0..6)
            val columnData = line.slice(7..9)
            var rowRange = 0..127
            var columnRange = 0..7

            rowData.forEach {
                rowRange = when (it) {
                    'F' -> upperHalf(rowRange)
                    'B' -> lowerHalf(rowRange)
                    else -> throw IllegalArgumentException("Not a valid character")
                }
            }

            columnData.forEach {
                columnRange = when (it) {
                    'L' -> upperHalf(columnRange)
                    'R' -> lowerHalf(columnRange)
                    else -> throw IllegalArgumentException("Not a valid character")
                }
            }
            listOf(rowRange.first * 8 + columnRange.first)
        }
    }

    override fun solvePart1(input: List<String>): String {
        return getSeatIds(input)
            .maxOrNull()
            ?.toString()
            ?: ""
    }

    override fun solvePart2(input: List<String>): String {
        return getSeatIds(input)
            .sorted()
            .zipWithNext()
            .first { it.second - it.first != 1 }
            .second
            .minus(1)
            .toString()
    }
}