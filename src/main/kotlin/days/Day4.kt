package days

open class DefaultRule(var value: String = "") {
    protected open fun isOptional(): Boolean = false
    open fun isValid(): Boolean {
        return if (isOptional()) true
        else value.isNotEmpty()
    }
}

class RuleByr : DefaultRule() {
    override fun isValid(): Boolean {
        if (value.isEmpty()) return false

        val year = value.toInt()
        return year in 1920..2002
    }
}

class RuleIyr : DefaultRule() {
    override fun isValid(): Boolean {
        if (value.isEmpty()) return false

        val year = value.toInt()
        return year in 2010..2020
    }
}

class RuleEyr : DefaultRule() {
    override fun isValid(): Boolean {
        if (value.isEmpty()) return false

        val year = value.toInt()
        return year in 2020..2030
    }
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
    override fun isValid(): Boolean {
        if (value.isEmpty()) return false

        val matchResult = Regex("^#([a-f0-9]{6})$").find(value)
        return matchResult != null
    }
}

class RuleEcl : DefaultRule() {
    override fun isValid(): Boolean {
        val acceptedColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        return acceptedColors.contains(value)
    }
}

class RulePid : DefaultRule() {
    override fun isValid(): Boolean {
        val matchResult = Regex("^(\\d{9})$").find(value)
        return matchResult != null
    }
}

class RuleCid : DefaultRule() {
    override fun isOptional() = true
}

class Passport(private val rules: HashMap<String, DefaultRule>) {

    fun processData(input: String) {
        input.split(" ")
            .map { elt ->
                elt.split(":").let {
                    rules[it[0]]?.value = it[1]
                }
            }
    }

    fun isValid(): Boolean {
        return rules.count { it.value.isValid() } == rules.size
    }
}

class Day4 : Day(4) {

    private fun getDataForPart1(): HashMap<String, DefaultRule> = hashMapOf(
        "byr" to DefaultRule(),
        "iyr" to DefaultRule(),
        "eyr" to DefaultRule(),
        "hgt" to DefaultRule(),
        "hcl" to DefaultRule(),
        "ecl" to DefaultRule(),
        "pid" to DefaultRule(),
        "cid" to RuleCid(),
    )

    private fun getDataForPart2(): HashMap<String, DefaultRule> = hashMapOf(
        "byr" to RuleByr(),
        "iyr" to RuleIyr(),
        "eyr" to RuleEyr(),
        "hgt" to RuleHgt(),
        "hcl" to RuleHcl(),
        "ecl" to RuleEcl(),
        "pid" to RulePid(),
        "cid" to RuleCid(),
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