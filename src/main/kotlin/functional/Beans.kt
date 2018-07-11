package functional

import com.samskivert.mustache.Mustache
import functional.web.Routes
import functional.web.UserHandler
import functional.web.view.MustacheResourceTemplateLoader
import functional.web.view.MustacheViewResolver
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.context.support.beans
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.RouterFunctions

fun beans() = beans {
	bean<UserHandler>()
	bean<Routes>()
	bean("webHandler") {
		RouterFunctions.toWebHandler(ref<Routes>().router(), HandlerStrategies.builder().viewResolver(ref()).build())
	}
	bean {
		val prefix = "classpath:/templates/"
		val suffix = ".mustache"
		val loader = MustacheResourceTemplateLoader(prefix, suffix)
		MustacheViewResolver(Mustache.compiler().withLoader(loader)).apply {
			setPrefix(prefix)
			setSuffix(suffix)
		}
	}
}
