package com.elliancarlos.aoc.problems.three


fun solveDay3(input: List<String>): String {
  val line = input.joinToString()

  data class ShouldAdd(val result: Int, val shouldAdd: Boolean)

  val mulRegex = Regex("""mul\(([0-9]+),([0-9]+)\)|don't\(\)|do\(\)""")
  val result = mulRegex.findAll(line).toList().fold(
    ShouldAdd(0, true)
  )
  { acc, matchResult ->
    if (matchResult.value == "don't()") return@fold ShouldAdd(acc.result, false)
    else if (matchResult.value == "do()") return@fold ShouldAdd(acc.result, true)
    else {
      if (acc.shouldAdd) {
        val result = matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt()
        ShouldAdd(acc.result + result, true)
      } else {
        acc
      }
    }
  }
    .result

  return "Day 3: $result"
}

fun solveDay3PartOne(input: List<String>): String {
  val line = input.joinToString()
  val mulRegex = Regex("mul\\(([0-9]+),([0-9]+)\\)")
  val result = mulRegex.findAll(line).map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }.sum()
  return "Day 3: $result"
}