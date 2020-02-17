package ru.skillbranch.devintensive.models

import android.util.Log

class Bender(var status:Status = Status.NORMAL, var question:Question = Question.BDAY) {

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question


    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {

        val validation:String? = validateAnswer(answer)
        Log.d("M-Bender","validation $validation" )
        return if (validation!=null) "$validation\n${question.question}" to status.color
            else if (question.answers.contains(answer.toLowerCase())) {
            question = question.nextQuestion()
            "Отлично - ты справился\n${question.question}" to status.color
        } else {
            status = status.nextStatus()
            if (status == Status.NORMAL) {
                "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
            } else {
                "Это неправильный ответ\n${question.question}" to status.color
            }
        }

    }

    fun validateAnswer(answer: String):String?{
        return when (question){
            Question.NAME -> {
                if (answer.first().isLowerCase()) return "Имя должно начинаться с заглавной буквы"
                else return null
            }

            Question.PROFESSION -> {
                if (answer.first().isUpperCase()) return "Профессия должна начинаться со строчной буквы"
                else return null
            }
            Question.MATERIAL -> {
                if (answer.contains(Regex("""\d"""))) "Материал не должен содержать цифр"
                else return null
            }
            Question.BDAY -> {
                if (answer.contains(Regex("""[^0-9]"""))) "Год моего рождения должен содержать только цифры"
                else return null
            }
            Question.SERIAL -> {
                if (answer.contains(Regex("""[^0-9]""")) || answer.length!=7)"Серийный номер содержит только цифры, и их 7"
                else return null
            }
            Question.IDLE -> {
                return null//игнорировать валидацию
            }
        }
    }


    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("bender", "Бендер")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("bender", "сгибальщик")) {
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом всё, вопросов больше нет", listOf()) {
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}