package com.learnreative.learningreactivespiring

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier
import java.lang.RuntimeException

class TestsWithFlux {
    @Test
    fun flux(){
        val flux = Flux.just("a","b","c").log()
        StepVerifier.create(flux)
            .expectNext("a","b","c")
            .verifyComplete()
    }
    @Test
    fun fluxWithError(){
        val flux = Flux.just("a","b","c").log()
            .concatWith(Flux.error(RuntimeException("Error")))
            .log()
        StepVerifier.create(flux)
            .expectNext("a","b","c")
            .expectError(RuntimeException::class.java)
            .verify()
    }

}