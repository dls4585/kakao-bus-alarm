package com.tonggeunbus.alarm

import okhttp3.OkHttpClient
import okhttp3.Request

fun main(args: Array<String>) {

    val client = OkHttpClient()
    val request = Request.Builder()
        .url("wss://kakao.e-bus.co.kr/websocket")
        .build()
    val listener = KakaoBusListener()

    client.newWebSocket(request, listener)

    client.dispatcher.executorService.shutdown()
}
