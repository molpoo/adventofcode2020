package days

class Day6 : Day(6, "Custom Customs") {

    private fun countAnswer(line: String, currentAnswers: List<Char>, filter: Boolean = true): List<Char> {
        return line
            .map { it }
            .filter { if (filter) !currentAnswers.contains(it) else true }
            .toList()
    }

    override fun solvePart1(input: List<String>): String {
        var numberOfRightAnswers = 0
        val currentAnswers = mutableListOf<Char>()
        input.forEach { line ->
            if (line.isBlank()) {
                numberOfRightAnswers += currentAnswers.size
                currentAnswers.clear()
            } else {
                currentAnswers += countAnswer(line.trim(), currentAnswers)
            }
        }
        numberOfRightAnswers += currentAnswers.size

        return numberOfRightAnswers.toString()
    }

    override fun solvePart2(input: List<String>): String {
        var numberOfRightAnswers = 0
        val currentAnswers = mutableListOf<Char>()
        var nbPersons = 0
        input.forEach { line ->
            if (line.isBlank()) {
                numberOfRightAnswers += currentAnswers
                    .distinct()
                    .map { answer ->
                        currentAnswers.count { it == answer } == nbPersons
                    }
                    .count { it }
                currentAnswers.clear()
                nbPersons = 0
            } else {
                nbPersons++
                currentAnswers += countAnswer(line.trim(), currentAnswers, false)
            }
        }
        numberOfRightAnswers += currentAnswers.size

        return numberOfRightAnswers.toString()
    }
}