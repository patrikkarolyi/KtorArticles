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

    val authors = mutableListOf<Author>()
    var loggedInAuthor = Author(
        id = 0,
        username = "Anonym",
        firstName = "John",
        lastName = "Doe",
        email = "none"
    )

    suspend fun getAuthors(call: ApplicationCall) {
        val builder = StringBuilder()
        for (author in authors) {
            builder
                .append(author.username)
                .append("/n")
        }
        call.respond(builder.toString())
    }

    suspend fun loginAuthor(call: ApplicationCall) {
        val username = call.getQuery<String>("username")
        val author = authors.firstOrNull { it.username==username } ?: httpException(HttpStatusCode.BadRequest)
        loggedInAuthor = author
        call.respond("logged in as ${author.username}")
    }

    suspend fun getAuthor(call: ApplicationCall) {
        val authorId = call.getPath<String>("authorId").toLong()
        val author = authors.firstOrNull { it.id==authorId } ?: httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)
        call.respond(author)
    }

    suspend fun updateAuthor(call: ApplicationCall) {
        val authorId = call.getPath<String>("authorId").toLong()
        val body = call.getBodyParam<Author>("body")
        authors.removeIf { it.id == authorId }
        authors.add(body)
        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)

        call.respond("updated author with id: $authorId")
    }

    suspend fun deleteAuthor(call: ApplicationCall) {
        val authorId = call.getPath<String>("authorId").toLong()
        authors.removeIf { it.id == authorId }

        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)

        call.respond("deleted author with id: $authorId")
    }

}