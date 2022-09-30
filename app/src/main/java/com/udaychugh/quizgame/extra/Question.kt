package com.udaychugh.quizgame.extra

data class Question(
    val id: Int,
    val question: String,
    val One: String,
    val Two: String,
    val Three: String,
    val Four: String,
    val correctOption: Int
)