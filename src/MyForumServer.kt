package hu.bme.koltin.mdt72t
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.swagger.experimental.getBodyParam
import io.ktor.swagger.experimental.getPath
import io.ktor.swagger.experimental.getQuery
import io.ktor.swagger.experimental.httpException
import java.util.*

/**
 * MyForum
 *
 * Some brief description.
 */
class MyForumServer {
    /**
     * forum
     */
    fun Routing.registerForum() {

        route("/forum") {

            get("/article") {
                call.respond(
                    "ListOFArticles"
                )
            }

            post("/article") {
                val body = call.getBodyParam<Article>("body")

                if (false) httpException(HttpStatusCode.BadRequest)

                call.respond(
                    Article(
                        id = 0,
                        authorId = 0,
                        publicationDate = Date(),
                        topic = "topic"
                    )
                )
            }

            get("/article/{articleId}") {
                val articleId = call.getPath<String>("articleId")

                if (false) httpException(HttpStatusCode.BadRequest)
                if (false) httpException(HttpStatusCode.NotFound)

                call.respond(
                    Article(
                        id = 0,
                        authorId = 0,
                        publicationDate = Date(),
                        topic = "myforumserver.kt"
                    )
                )
            }
        }


    }

    /**
     * author
     */
    fun Routing.registerAuthor() {

        route("/aurhor") {

            post("/") {
                val body = call.getBodyParam<Author>("body")

                call.respond("returned author")
            }

            get("/login") {
                val username = call.getQuery<String>("username")

                if (false) httpException(HttpStatusCode.BadRequest)

                call.respond("logged in")
            }

            get("/{authorId}") {
                val authorId = call.getPath<String>("authorId")

                if (false) httpException(HttpStatusCode.BadRequest)
                if (false) httpException(HttpStatusCode.NotFound)

                call.respond(
                    Author(
                        id = 0,
                        username = "patrikkarolyi",
                        firstName = "Patrik",
                        lastName = "Karolyi",
                        email = "email"
                    )
                )
            }

            put("/{authorId}") {
                val authorId = call.getPath<String>("authorId")
                val body = call.getBodyParam<Author>("body")

                if (false) httpException(HttpStatusCode.BadRequest)
                if (false) httpException(HttpStatusCode.NotFound)

                call.respond("updated author with id: $authorId")
            }

            delete("/{authorId}") {
                val authorId = call.getPath<String>("authorId")

                if (false) httpException(HttpStatusCode.BadRequest)
                if (false) httpException(HttpStatusCode.NotFound)

                call.respond("deleted author with id: $authorId")
            }
        }
    }
}
