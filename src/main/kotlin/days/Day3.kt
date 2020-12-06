package days

class Day3 : Day(3, "Toboggan Trajectory") {

    inner class SlopeRules(val right: Int, val down: Int)
    inner class Location(
        var x: Int,
        var y: Int,
        var maxHeight: Int,
        var maxWidth: Int,
        private val slopeRules: SlopeRules
    ) {
        fun move(): Boolean {
            if (y + slopeRules.down >= maxHeight) return false

            x = (x + slopeRules.right) % maxWidth
            y += slopeRules.down
            return true
        }
    }

    inner class Forest(private val input: List<String>, private val location: Location) {
        private var treesEncountered = 0

        fun getTreesEncountered(): Int {
            input.forEach { _ ->
                if (location.move()) {
                    if (isTree(location)) treesEncountered++
                }
            }
            return treesEncountered
        }

        private fun isTree(location: Location): Boolean {
            return input[location.y][location.x] == '#'
        }
    }

    override fun solvePart1(input: List<String>): String {
        val location = Location(0, 0, input.size, input.first().length, SlopeRules(3, 1))
        val forest = Forest(input, location)

        return forest.getTreesEncountered().toString()
    }

    override fun solvePart2(input: List<String>): String {
        val slopeRulesList = listOf(
            SlopeRules(1, 1),
            SlopeRules(3, 1),
            SlopeRules(5, 1),
            SlopeRules(7, 1),
            SlopeRules(1, 2),
        )
        var treesEncountered = 1L
        slopeRulesList.forEach { slopeRules ->
            val location = Location(0, 0, input.size, input.first().length, slopeRules)
            val forest = Forest(input, location)
            treesEncountered *= forest.getTreesEncountered()
        }

        return treesEncountered.toString()
    }
}