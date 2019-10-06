package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Author
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.swagger.experimental.getBodyParam
import io.ktor.swagger.experimental.getPath
import io.ktor.swagger.experimental.getQuery
import io.ktor.swagger.experimental.httpException

class AuthorRoute {
    suspend fun getAuthors(call: ApplicationCall) {
        val body = call.getBodyParam<Author>("body")

        call.respond("returned author")
    }

    suspend fun loginAuthor(call: ApplicationCall) {
        val username = call.getQuery<String>("username")

        if (false) httpException(HttpStatusCode.BadRequest)

        call.respond("logged in")
    }

    suspend fun getAuthor(call: ApplicationCall) {
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

    suspend fun updateAuthor(call: ApplicationCall) {
        val authorId = call.getPath<String>("authorId")
        val body = call.getBodyParam<Author>("body")

        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)

        call.respond("updated author with id: $authorId")
    }

    suspend fun deleteAuthor(call: ApplicationCall) {
        val authorId = call.getPath<String>("authorId")

        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)

        call.respond("deleted author with id: $authorId")
    }

}