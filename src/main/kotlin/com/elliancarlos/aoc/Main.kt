package com.elliancarlos.aoc

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.int

fun main(args: Array<String>) = AdventOfCode().main(args)

class AdventOfCode : CliktCommand() {
    private val day: Int? by option(help = "Specify the day of Advent of Code to run").int()

    override fun run() {
        val dayToRun = day ?: 1 // Default to Day 1 if no day is provided
        println("Running solution for Day $dayToRun")
        when (dayToRun) {
            1 -> runDay1()
            2 -> runDay2()
            // Add more days as needed
            else -> println("Solution for Day $dayToRun is not yet implemented.")
        }
    }

    private fun runDay1() {
        val input = getInput(1)
        val result = solveDay1(input)
        println("Day 1 solution: $result")
    }

    private fun runDay2() {
        val input = getInput(2)
        val result = solveDay2(input)
        println("Day 2 solution: $result")
    }

    // Add more day-specific functions here

    private fun getInput(day: Int): List<String> {
        val filename = "inputs/day${day.toString().padStart(2, '0')}.txt"
        return this::class.java.classLoader.getResource(filename)?.readText()?.lines()
            ?: error("Input file for Day $day not found.")
    }
}