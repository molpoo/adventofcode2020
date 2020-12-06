package days

fun IntRange.upperHalf(): IntRange {
    return this.first..this.last - ((this.last - this.first) / 2)
}

fun IntRange.lowerHalf(): IntRange {
    return this.last - (this.last - this.first) / 2..this.last
}

class Day5 : Day(5, "Binary Boarding") {

    private fun getSeatIds(input: List<String>): List<Int> {
        return input.flatMap { line ->
            val rowData = line.slice(0..6)
            val columnData = line.slice(7..9)
            var rowRange = 0..127
            var columnRange = 0..7

            rowData.forEach {
                when (it) {
                    'F' -> rowRange = rowRange.upperHalf()
                    'B' -> rowRange = rowRange.lowerHalf()
                }
            }

            columnData.forEach {
                when (it) {
                    'L' -> columnRange = columnRange.upperHalf()
                    'R' -> columnRange = columnRange.lowerHalf()
                }
            }
            listOf(rowRange.first * 8 + columnRange.first)
        }
    }

    override fun solvePart1(input: List<String>): String {
        return getSeatIds(input).maxOrNull()!!.toString()
    }

    override fun solvePart2(input: List<String>): String {
        val seatIDs = getSeatIds(input).sorted()

        var currentElement = seatIDs.first()
        return seatIDs
            .first { seatId ->
                if ((seatId - currentElement) > 1) true
                else {
                    currentElement = seatId
                    false
                }
            }
            .minus(1)
            .toString()
    }
}