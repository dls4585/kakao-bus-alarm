package com.tonggeunbus.alarm

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BusInfo(
    val boardingDay: String,
    val bookCount: Int,
    val availYn: String,
) {
    fun isAvailable() = availYn == "Y"
}
