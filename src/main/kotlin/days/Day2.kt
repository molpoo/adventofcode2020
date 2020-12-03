class Day2 : Day(2) {

    override fun solvePart1(input: List<String>): String {
        var numberOfPasswordValid = 0
        input.forEach { line ->
            val (min, max, letter, password) = Regex("(\\d+)-(\\d+) (.): (.+)").find(line)!!.destructured

            val occurence = password.count { it == letter[0] }
            if (occurence >= min.toInt() && occurence <= max.toInt()) {
                numberOfPasswordValid++
            }
        }

        return numberOfPasswordValid.toString()
    }

    override fun solvePart2(input: List<String>): String {
        var numberOfPasswordValid = 0
        input.forEach { line ->
            val (min, max, letter, password) = Regex("(\\d+)-(\\d+) (.): (.+)").find(line)!!.destructured

            val isPositionOneValid = password[min.toInt()-1].toString() == letter
            val isPositionTwoValid = password[max.toInt()-1].toString() == letter
            if((isPositionOneValid && !isPositionTwoValid) || (!isPositionOneValid && isPositionTwoValid)) {
                numberOfPasswordValid++
            }
        }

        return numberOfPasswordValid.toString()
    }
}