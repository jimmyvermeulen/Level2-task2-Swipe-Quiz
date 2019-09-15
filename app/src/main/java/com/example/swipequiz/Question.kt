package com.example.swipequiz

data class Question(
    var statement : String,
    var answer : Boolean
) {
    companion object {
        val QUESTION_STATEMENTS = arrayOf(
            "A val and a var are the same",
            "Mobile Application Development grants 12 ECTS",
            "A Unit in Kotlin corresponds to a void in Java",
            "In Kotlin 'when' replaces the 'switch' operator in Java"
        )

        val QUESTION_ANSWERS = arrayOf(
            false,
            false,
            true,
            true
        )
    }
}