package com.tonggeunbus.alarm

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AlarmApplication

fun main(args: Array<String>) {
	runApplication<AlarmApplication>(*args)
}
