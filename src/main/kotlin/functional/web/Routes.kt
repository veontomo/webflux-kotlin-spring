package functional.web

import org.springframework.http.MediaType.*
import org.springframework.web.reactive.function.server.router

class Routes(private val dataHandler: DataHandler) {

    fun router() = router {
        "/data".nest {
            accept(APPLICATION_JSON).nest {
                GET("/repeat", dataHandler::stream)
            }
            accept(APPLICATION_JSON).nest {
                GET("/all", dataHandler::getAll)
            }
            accept(TEXT_EVENT_STREAM).nest {
                GET("/", dataHandler::stream)
            }

        }
    }
}
