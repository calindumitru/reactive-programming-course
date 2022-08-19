package it.dumitru.reactor.playground

import com.github.javafaker.Faker
import mu.KotlinLogging

object FakerUtil {
    val FAKER = Faker.instance()
}

private val logger = KotlinLogging.logger {}

fun onNextLog(prefix: String): (t: String) -> Unit = { logger.info { "$prefix: $it" } }

fun onSuccessLogCompleted(): () -> Unit = { logger.info { "Completed" } }

fun onError(): (t: Throwable) -> Unit = { logger.catching(it) }