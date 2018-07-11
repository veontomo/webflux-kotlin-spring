package functional.web

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import reactor.core.publisher.Flux
import java.time.Duration

@Suppress("UNUSED_PARAMETER")
class DataHandler {

	private val data = Flux.just(1, 2, 3)

	private val dataStream = Flux
			.zip(Flux.interval(Duration.ofMillis(1000)), data.repeat())
			.map { it.t2 }

	fun getAll(req: ServerRequest) =
			ok().body(data)

	fun stream(req: ServerRequest) =
			ok().bodyToServerSentEvents(dataStream)

}


