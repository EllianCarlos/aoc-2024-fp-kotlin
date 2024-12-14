package com.elliancarlos.aoc.problems.two

import kotlin.math.abs


fun solveDay2(input: List<String>): String {
  val result = input
    .map(::readLineAsList)
    .let {
      (1..it.size).associateWith { index -> it[index - 1] }
    }
    .let(::createListsRemovingOneFromMap)
    .entries.map { (key, value) ->
      value.map(::evaluateLine).also {
        if (it.contains(Operation.SAFE)) return@map Operation.SAFE
        else return@map Operation.UNSAFE
      }
    }.fold(0) {
      acc, op -> if (op == Operation.SAFE) return@fold acc + 1 else acc
    }

  return "Solution for Day 2 is $result"
}

fun createListsRemovingOneFromMap(list: Map<Int, List<Int>>): Map<Int, List<List<Int>>> =
  list.keys.associateWith { index -> createListsRemovingOne(list[index] ?: emptyList()) }

fun createListsRemovingOne(list: List<Int>): List<List<Int>> {
  return list.indices.map { index -> list.toMutableList().filterIndexed { i, _ -> i != index } }
}

data class LastResult(val lastNumber: Int, val wasDecreasing: Boolean?)

enum class Operation {
  SAFE, UNSAFE
}

fun solveDay2PartOne(input: List<String>): String {
  val result = input
    .map(::readLineAsList)
    .map { line: List<Int> ->
      evaluateLine(line)
    }
    .fold(0) { acc, op ->
      if (op == Operation.SAFE) return@fold acc + 1 else acc
    }


  return "Solution for Day 2 is $result"
}

private enum class EvaluationType {
  INCREASING, DECREASING
}

fun evaluateLine(line: List<Int>): Operation {
  val maps = line.windowed(2).map<List<Int>, EvaluationType> { (first, second) ->
    val difference = abs(first - second)
    if (difference < 1 || difference > 3) return@evaluateLine Operation.UNSAFE
    if (first > second) return@map EvaluationType.DECREASING
    else return@map EvaluationType.INCREASING
  }.fold<EvaluationType, Map<EvaluationType, Int>>(
    mapOf(
      EvaluationType.INCREASING to 0,
      EvaluationType.DECREASING to 0
    )
  ) { acc, evaluationType ->
    acc + (evaluationType to (acc[evaluationType] ?: 0) + 1)
  }

  val isUnsafe = ((maps[EvaluationType.INCREASING] ?: 0) >= 1).and((maps[EvaluationType.DECREASING] ?: 0) >= 1)
  if (isUnsafe) return Operation.UNSAFE
  return Operation.SAFE
}

fun readLineAsList(line: String): List<Int> = line.split(" ").map(String::toInt)
