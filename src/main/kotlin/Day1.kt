class Day1 : Day(1) {

    override fun solvePart1(input: List<String>): String {
        val inputToInt = input.map(String::toInt)
        inputToInt.forEach { expense ->
            val elementToFind = 2020 - expense
            if (inputToInt.indexOf(elementToFind) > -1) {
                return (expense * elementToFind).toString()
            }
        }
        throw IllegalStateException()
    }

    override fun solvePart2(input: List<String>): String {
        val inputToInt = input.map(String::toInt)
        inputToInt.forEach { expense ->
            inputToInt.forEach { expense2 ->
                inputToInt.forEach { expense3 ->
                    if (expense + expense2 + expense3 == 2020)
                        return (expense * expense2 * expense3).toString()
                }
            }
        }
        throw IllegalStateException()
    }

}