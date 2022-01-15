package com.metu.fers

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class FersApplication

fun main(args: Array<String>) {
    runApplication<FersApplication>(*args)
}
