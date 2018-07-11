package functional.web

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.*
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToServerSentEvents
import reactor.core.publisher.Flux
import java.time.Duration

@Suppress("UNUSED_PARAMETER")
class UserHandler {

	private val users = Flux.just(1, 2, 3)

	private val userStream = Flux
			.zip(Flux.interval(Duration.ofMillis(100)), users.repeat())
			.map { it.t2 }

	fun findAll(req: ServerRequest) =
			ok().body(users)

	fun stream(req: ServerRequest) =
			ok().bodyToServerSentEvents(userStream)

}


