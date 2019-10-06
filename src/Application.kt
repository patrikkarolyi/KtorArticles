package hu.bme.koltin.mdt72t

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.thymeleaf.Thymeleaf
import io.ktor.thymeleaf.ThymeleafContent
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.auth.*
import io.ktor.swagger.experimental.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }

    install(ContentNegotiation) {
        gson {
        }

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val client = HttpClient(Apache) {
    }

    install(Authentication) {
    }

    routing {
        get("/") {
            call.respondText("myHELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/html-thymeleaf") {
            val content = mapOf("movie" to ThymeleafUser(1, "user1"))
            call.respond(ThymeleafContent("index", content))
        }

        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        install(StatusPages) {

            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }
            exception<HttpException> {  cause ->
                call.respond(cause.code, cause.description)
            }
        }

        MyForumServer().apply {
            registerForum()
            registerAuthor()
        }
    }
}

data class ThymeleafUser(val id: Int, val name: String)

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

