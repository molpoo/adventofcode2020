package days

class Day4 : Day(4, "Passport Processing") {

    open class DefaultRule(var value: String = "") {
        protected open fun isOptional() = false
        open fun isValid() = isOptional() || value.isNotEmpty()
    }

    class RuleByr : DefaultRule() {
        override fun isValid() = value.isNotEmpty() && value.toInt() in 1920..2002
    }

    class RuleIyr : DefaultRule() {
        override fun isValid() = value.isNotEmpty() && value.toInt() in 2010..2020
    }

    class RuleEyr : DefaultRule() {
        override fun isValid() = value.isNotEmpty() && value.toInt() in 2020..2030
    }

    class RuleHgt : DefaultRule() {
        override fun isValid(): Boolean {
            if (value.isEmpty()) return false

            val (height, unit) = Regex("^(\\d+)(.+)$").find(value)!!.destructured
            return when (unit) {
                "cm" -> height.toInt() in 150..193
                "in" -> height.toInt() in 59..76
                else -> false
            }
        }
    }

    class RuleHcl : DefaultRule() {
        override fun isValid() = Regex("^#([a-f0-9]{6})$").matches(value)
    }

    class RuleEcl : DefaultRule() {
        override fun isValid() = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
    }

    class RulePid : DefaultRule() {
        override fun isValid() = Regex("^(\\d{9})$").matches(value)
    }

    class RuleCid : DefaultRule() {
        override fun isOptional() = true
    }

    class Passport(private val rules: HashMap<String, DefaultRule>) {

        fun processData(input: String) {
            input.split(" ")
                .map(::applyData)
        }

        private fun applyData(data: String) {
            data.split(":").let {
                rules[it[0]]?.value = it[1]
            }
        }

        fun isValid(): Boolean {
            return rules.count { it.value.isValid() } == rules.size
        }
    }

    private fun getDataForPart1(): HashMap<String, DefaultRule> = hashMapOf(
        "byr" to DefaultRule(),
        "iyr" to DefaultRule(),
        "eyr" to DefaultRule(),
        "hgt" to DefaultRule(),
        "hcl" to DefaultRule(),
        "ecl" to DefaultRule(),
        "pid" to DefaultRule(),
        "cid" to RuleCid()
    )

    private fun getDataForPart2(): HashMap<String, DefaultRule> = hashMapOf(
        "byr" to RuleByr(),
        "iyr" to RuleIyr(),
        "eyr" to RuleEyr(),
        "hgt" to RuleHgt(),
        "hcl" to RuleHcl(),
        "ecl" to RuleEcl(),
        "pid" to RulePid(),
        "cid" to RuleCid()
    )

    private fun solve(input: List<String>, getRules: () -> HashMap<String, DefaultRule>): String {
        var passportValid = 0
        var passport = Passport(getRules())
        input.map(String::trim)
            .forEach { line ->
                if (line.isNotEmpty()) {
                    passport.processData(line)
                } else {
                    if (passport.isValid()) {
                        passportValid++
                    }
                    passport = Passport(getRules())
                }
            }

        return passportValid.toString()
    }

    override fun solvePart1(input: List<String>): String {
        return solve(input, ::getDataForPart1)
    }

    override fun solvePart2(input: List<String>): String {
        return solve(input, ::getDataForPart2)
    }
}