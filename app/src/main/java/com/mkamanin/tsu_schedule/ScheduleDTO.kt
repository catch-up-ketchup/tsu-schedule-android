package com.mkamanin.tsu_schedule

data class ScheduleDTO(
    val schedule: List<ScheduleItem>,
    val group: String,
)

data class ScheduleItem(
    val day: Day,
    val dailySchedule: List<DailySchedule>
)

data class Day(
    val weekType: String,
    val dayOfWeek: String,
    val date: String
)

data class Time(
    val start: String,
    val end: String
)

data class Type(
    val ru: String,
    val en: String,
    val subgroup: String
)

data class DailySchedule(
    val subject: String,
    val time: Time,
    val auditorium: String,
    val teacher: String,
    val type: Type
)
