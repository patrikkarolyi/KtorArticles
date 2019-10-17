package hu.bme.koltin.mdt72t.routes
import hu.bme.koltin.mdt72t.HttpException
import hu.bme.koltin.mdt72t.MyForumServer
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.routing


class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

fun Application.registerRouting(){
    routing {

        install(StatusPages) {

            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }
            exception<HttpException> { cause ->
                call.respond(cause.code, cause.description)
            }
        }

        MyForumServer().apply {
            registerArticle()
            registerAuthor()
            registerDemo()
        }
    }
}