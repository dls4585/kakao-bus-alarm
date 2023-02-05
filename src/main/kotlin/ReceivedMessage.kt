package com.tonggeunbus.alarm

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ReceivedMessage(
    val serverId: String?,
    val methods: List<String>?,
    val session: String?,
    val id: String?,
    val msg: String?,
    val result: List<BusInfo>?,
)
