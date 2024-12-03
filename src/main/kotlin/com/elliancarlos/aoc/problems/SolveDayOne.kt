package com.elliancarlos.aoc.problems

import kotlin.math.abs

fun solveDay1(input: List<String>): String {
    TODO()
}

fun solveDay1PartOne(input: List<String>): String {
    val maxComparator = Comparator { a: Int, b: Int -> a - b }
    val minComparator = Comparator { a: Int, b: Int -> b - a }

    val firstNumbers = getNumbersInOrder(input, breakInputLineBy { it.first() }, maxComparator)
    val secondNumbers = getNumbersInOrder(input, breakInputLineBy { it.last() }, maxComparator)

    println(firstNumbers.zip(secondNumbers).sumOf { (firstNumber, secondNumber) -> abs(firstNumber - secondNumber) })

    return "Solution for Day 1 (example)"
}

private fun breakInputLineBy(numberChooser: (List<String>) -> String) =
    { inputLine: String -> inputLine.split(" ").let(numberChooser).toInt() }

private fun getNumbersInOrder(
    input: List<String>,
    extractor: (String) -> Int,
    sortStrategy: Comparator<Int>,
): List<Int> = input.map(extractor).sortedWith(sortStrategy)
