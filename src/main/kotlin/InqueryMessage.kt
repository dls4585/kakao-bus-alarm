package com.tonggeunbus.alarm

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class InqueryMessage(
    val msg: String? = null,
    val id: String? = null,
    val method: String? = null,
    val params: List<Param>? = null,
)
