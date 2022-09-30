package com.udaychugh.quizgame.extra

object Constants {
    fun getQuestions() : ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val question1 = Question(
            1,
            "Which one of the following river flows between Vindhyan and Satpura ranges?",
            "Narmada",
            "Mahanadi",
            "Son",
            "Netravati",
            1
        )
        questionsList.add(question1)

        val question2 = Question(
            1,
            "The Central Rice Research Station is situated in?",
            "Chennai",
            "Quilon",
            "Bangalore",
            "Cuttack",
            4
        )
        questionsList.add(question2)

        val question3 = Question(
            1,
            "Who among the following wrote Sanskrit grammar?",
            "Aryabhatt",
            "Panini",
            "Charak",
            "Kalidasa",
            2
        )
        questionsList.add(question3)

        val question4 = Question(
            1,
            "Which one of the following are the city of US",
            "New York",
            "Portland",
            "Pune",
            "Mumbai",
            1
        )
        questionsList.add(question4)

        val question5 = Question(
            1,
            "What is weight of earth ?",
            "5.972 × 10^24 kg",
            "5.972 × 10^21 t",
            "Infinite",
            "1.317 × 10^25 lbs",
            1
        )
        questionsList.add(question5)
        return questionsList
    }
}