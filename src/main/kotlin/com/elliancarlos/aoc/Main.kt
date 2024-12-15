package com.elliancarlos.aoc

import com.elliancarlos.aoc.problems.one.solveDay1
import com.elliancarlos.aoc.problems.three.solveDay3
import com.elliancarlos.aoc.problems.two.solveDay2
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int
import java.io.File

fun main(args: Array<String>) = AdventOfCode().main(args)

class AdventOfCode : CliktCommand() {
  private val day: Int? by option(help = "Specify the day of Advent of Code to run").int()

  override fun run() {
    val dayToRun = day ?: 1 // Default to Day 1 if no day is provided
    println("Running solution for Day $dayToRun")
    runDay(dayToRun)
  }

  private fun runDay(day: Int) {
    val input = getInput(day)
    val result = when (day) {
      1 -> solveDay1(input)
      2 -> solveDay2(input)
      3 -> solveDay3(input)
      // Add more days as needed
      else -> "Solution for Day $day is not yet implemented."
    }
    println("Day $day solution: $result")
  }

  private fun getInput(day: Int): List<String> {
    val filename = "inputs/day${day.toString().padStart(2, '0')}.txt"
    return File(filename).readLines()
  }
}
