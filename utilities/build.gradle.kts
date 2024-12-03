plugins {
    kotlin("jvm") version "1.9.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin Standard Library
    implementation(kotlin("stdlib"))

    // Command-line parser (optional, for handling input arguments)
    implementation("com.github.ajalt.clikt:clikt:4.0.0")
}

application {
    // Set the main class to your entry point
    mainClass.set("com.example.aoc.MainKt")
}

// Optional: Configure Java toolchain if needed
kotlin {
    jvmToolchain(17)
}

// Optional: Task for running a specific day
tasks.register("runDay") {
    description = "Runs the solution for a specific day of Advent of Code"
    group = "application"
    val day = project.findProperty("day")?.toString() ?: "1"
    doLast {
        println("Running solution for day: $day")
        // Insert logic to execute a specific day's solution dynamically if needed
    }
}
