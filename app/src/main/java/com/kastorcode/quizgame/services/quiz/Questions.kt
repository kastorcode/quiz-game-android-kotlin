package com.kastorcode.quizgame.services.quiz


class Questions {

    private val questions : Array<String> = arrayOf(
        "Which is the latest version of Android?",
        "Which version is the most widely used?",
        "From which version did the ART become the default runtime?"
    )

    private val choices : Array<String> = arrayOf("Lollipop", "Android 11", "Pie", "Froyo")

    private val correctAnswers : Array<String> = arrayOf("Android 11", "Pie", "Lollipop")


    fun getQuestion (index : Int) : String? {
        if (index > -1 && index < questions.size) {
            return questions[index]
        }
        return null
    }


    fun getChoices () : Array<String> {
        val copy = choices.copyOf()
        copy.shuffle()
        return copy
    }


    fun getCorrectAnswer (index : Int) : String? {
        if (index > -1 && index < correctAnswers.size) {
            return correctAnswers[index]
        }
        return null
    }

}