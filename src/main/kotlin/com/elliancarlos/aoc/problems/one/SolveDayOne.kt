package com.elliancarlos.aoc.problems.one

import kotlin.math.abs

fun solveDay1(input: List<String>): String {
  val maxComparator = Comparator { a: Int, b: Int -> a - b }

  val firstNumbers = getNumbers(input, breakInputLineBy { it.first() })
  val secondNumbers = getNumbers(input, breakInputLineBy { it.last() })

  val result = getFrequencyMap(secondNumbers).let { firstNumbers.map { number -> (it[number] ?: 0)* number }}.sum()

  return "Full solution for Day 1 is $result"
}

fun getMultiplicationSummedFrequencyMap(input: List<Int>): Int =
  input.let(::getFrequencyMap).let(::multiplyKeyToValue)

fun multiplyKeyToValue(map: Map<Int, Int>): Int = map.entries.sumOf { it.key * it.value }

fun getFrequencyMap(input: List<Int>): Map<Int, Int> =
  input.fold(emptyMap()) { acc, number ->
    acc + (number to (acc[number] ?: 0) + 1)
  }

fun solveDay1PartOne(input: List<String>): String {
  val maxComparator = Comparator { a: Int, b: Int -> a - b }
  val minComparator = Comparator { a: Int, b: Int -> b - a }

  val firstNumbers = getNumbersInOrder(input, breakInputLineBy { it.first() }, maxComparator)
  val secondNumbers = getNumbersInOrder(input, breakInputLineBy { it.last() }, maxComparator)

  val result = firstNumbers.zip(secondNumbers).sumOf { (firstNumber, secondNumber) -> abs(firstNumber - secondNumber) }
  println(result)

  return "Solution for Day 1 is $result"
}

fun breakInputLineBy(numberChooser: (List<String>) -> String) =
  { inputLine: String -> inputLine.split(" ").let(numberChooser).toInt() }

fun getNumbers(
  input: List<String>,
  extractor: (String) -> Int,
) = input.map(extractor)

fun getNumbersInOrder(
  input: List<String>,
  extractor: (String) -> Int,
  sortStrategy: Comparator<Int>,
): List<Int> = getNumbers(input, extractor).sortedWith(sortStrategy)
