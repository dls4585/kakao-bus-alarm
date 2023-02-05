package com.tonggeunbus.alarm

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Param(
    val userSeq: Int, // user_id
    val startDay: String,
    val endDay: String,
    val lineSeq: Int, // 노선 id
    val stopSeqOn: Int, // 타는 정류장 id
    val stopSeqOff: Int, // 내리는 정류장 id
)
