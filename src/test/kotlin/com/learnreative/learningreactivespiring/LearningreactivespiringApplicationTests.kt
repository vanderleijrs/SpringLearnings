package com.learnreative.learningreactivespiring

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import java.time.Duration
import kotlin.RuntimeException
import kotlin.io.println


@SpringBootTest
class LearningreactivespiringApplicationTests {

	@Test
	fun justFluxPrint() {
		val flux = Flux.just("a","b","c")
			.subscribe{data-> println(data) }
	}
	@Test
	fun FluxWithError() {
		val flux = Flux.just("a","b","c")
			.concatWith(Flux.error(RuntimeException("Some exception occured")))
			.subscribe({data-> println(data) },{ error-> println("Error is $error") },{ println("Completed") })
	}
	@Test
	fun justPrintWithInterable() {
		Flux.fromIterable(listOf("a","b","c"))
			.subscribe{data-> println(data) }
	}
	@Test
	fun fluxWithRange() {
		Flux.range(5,6)
			.subscribe{data-> println(data) }
	}
	@Test
	fun fluxFromInterval() {
		Flux.interval(Duration.ofSeconds(1))
			.subscribe{data-> println(data) }
		Thread.sleep(10000)
	}
	@Test
	fun fluxWithTakeOperator() {
		Flux.interval(Duration.ofSeconds(1))
			.take(2)
			.subscribe{data-> println(data) }
		Thread.sleep(1000)
	}
	@Test
	fun fluxWithRequest() {
		Flux.range(1,9)
			.subscribe({data-> println(data) },{},{},{sub->sub.request(3)})
		Thread.sleep(1000)
	}
	@Test
	fun fluxWithErrorHandling() {
		Flux.just("a","b","c")
			.concatWith(Flux.error(RuntimeException("Some Exception")))
			.onErrorReturn("Some item on exception")
			.subscribe{data-> println(data)}
		Thread.sleep(1000)
	}

}
