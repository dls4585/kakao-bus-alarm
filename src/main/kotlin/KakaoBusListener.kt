package com.tonggeunbus.alarm

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

private val objectMapper = jacksonObjectMapper()

class KakaoBusListener(
    // TODO : sys env로 빼기
    private val userSeq: Int = 10697,
    private val lineSeq: Int = 3,
    private val stopSeqOn: Int = 1005,
    private val stopSeqOff: Int = 2040,
): WebSocketListener() {
    
    override fun onOpen(webSocket: WebSocket, response: Response) {
        webSocket.sendAndLog(CONNECT_MESSAGE)
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        println("Receiving : $text")
        val received: ReceivedMessage = objectMapper.readValue(text, ReceivedMessage::class.java)

        if (received.msg == null) {
            return
        }

        // TODO : 별도 클래스로 분리
        process(webSocket, received)

        // TODO : 알림 어떻게 ?
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        println("Receiving bytes : $bytes")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        println("Closing : $code / $reason")
        webSocket.close(code, reason)
        webSocket.cancel()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        println("Error : $t.message")
    }

    private fun process(webSocket: WebSocket, received: ReceivedMessage) {
        when(received.msg) {
            "connected" -> {
                // 버스 정보 요청
                val param = Param(
                    userSeq = userSeq,
                    startDay = "20230206",
                    endDay = "20230228",
                    lineSeq = lineSeq,
                    stopSeqOn = stopSeqOn,
                    stopSeqOff = stopSeqOff,
                )
                // TODO : id 관리 ?
                id = "10"
                webSocket.sendAndLog(
                    objectMapper.writeValueAsString(
                        InqueryMessage(
                            msg = "method",
                            id = id,
                            method = DATA_INQUERY_METHOD,
                            params = listOf(param)
                        )
                    )
                )
            }
            "result" -> {
                // 결과 파싱
                println(received.result)
                webSocket.close(NORMAL_CLOSURE_STATUS, null)
            }
            "ping" -> {
                webSocket.sendAndLog(
                    objectMapper.writeValueAsString(InqueryMessage(msg = "pong"))
                )
            }
            null -> return
        }
    }

    companion object {
        private const val CONNECT_MESSAGE = "{\"msg\":\"connect\",\"version\":\"1\",\"support\":[\"1\"]}"
        private const val NORMAL_CLOSURE_STATUS = 1000
        private const val DATA_INQUERY_METHOD = "getKakaoBookDataRenew"

        private var id: String? = null
    }
}

private fun WebSocket.sendAndLog(message: String) {
    this.send(message)
    println("Sent: $message")
}
