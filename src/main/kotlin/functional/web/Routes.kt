package functional.web

import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.router

class Routes(private val userHandler: UserHandler) {

    fun router() = router {
        "/api".nest {
            accept(APPLICATION_JSON).nest {
                GET("/users", userHandler::findAll)
            }
            accept(TEXT_EVENT_STREAM).nest {
                GET("/users", userHandler::stream)
            }

        }
    }
}
