package it.dumitru.reactor.playground

import mu.KotlinLogging
import reactor.core.publisher.Mono


private val logger = KotlinLogging.logger {}

fun main() {
    monoJust()
}

fun monoJust() {
    val mono = Mono.just(1)
    mono.subscribe{ logger.info { "Value: $it" }}
}

fun monoSubscribe() {
    val mono = Mono.just("ball")

    //    Subscribe to this Mono and request unbounded demand.
    //    This version doesn't specify any consumption behavior for the events from the chain, especially no error handling, so other variants should usually be preferred.
    //
    //    Returns:
    //    a new Disposable that can be used to cancel the underlying Subscription
    mono.subscribe()

    //region    Subscribe a Consumer to this Mono that will consume all the sequence.

    //    Param: consumer the consumer to invoke on each value (onNext signal)
    mono.subscribe { logger.info { "Value: $it" }}

    //    Params:
    //    consumer – the consumer to invoke on each next signal
    //    errorConsumer – the consumer to invoke on error signal
    mono.subscribe({ logger.info { "Value: $it" } }, onError())

    //    Params:
    //    consumer – the consumer to invoke on each value
    //    errorConsumer – the consumer to invoke on error signal
    //    completeConsumer – the consumer to invoke on complete signal
    mono.subscribe(onNextLog("Value"), onError(), onSuccessLogCompleted())
    //endregion
}