package it.dumitru.reactor.playground

import reactor.core.publisher.Mono


fun main() {
    monoRepo()
}

fun monoRepo() {
    userRepository(1).subscribe(onNextLog("User"), onError(), onSuccessLogCompleted())
}

fun userRepository(userId: Int): Mono<String> {
    return when (userId) {
        1 -> Mono.just(FakerUtil.FAKER.name().firstName())
        2 -> Mono.empty()
        else -> throw IllegalArgumentException("No user for id: $userId")
    }
}