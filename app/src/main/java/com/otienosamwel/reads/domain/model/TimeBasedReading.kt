package com.otienosamwel.reads.domain.model

import java.util.*

class TimeBasedReadingUseCase {
    fun getTimeOfTheDayMessage(): String {
        return when (getTimeOfTheDay()) {
            TimeOfTheDay.MORNING -> "Some light reads to get you started with your morning."
            TimeOfTheDay.AFTERNOON -> "Enjoy your afternoon break with a solid read."
            TimeOfTheDay.EVENING -> "How about ending your day on a high reading note."
        }
    }

    private fun getTimeOfTheDay(): TimeOfTheDay {
        val calendar = Calendar.getInstance()

        when (calendar.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> return TimeOfTheDay.MORNING
            in 12..17 -> return TimeOfTheDay.AFTERNOON
            in 18..23 -> return TimeOfTheDay.EVENING
        }

        return TimeOfTheDay.MORNING
    }
}

enum class TimeOfTheDay {
    MORNING,
    AFTERNOON,
    EVENING
}