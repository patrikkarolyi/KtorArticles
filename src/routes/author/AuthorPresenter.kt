package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Author
import hu.bme.koltin.mdt72t.db.AuthorInteractor
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import util.getBodyParam
import util.getPath
import util.getQuery
import util.httpException
import routes.author.AuthorViewState

class AuthorPresenter {

    val interactor = AuthorInteractor()
    var state = AuthorViewState()

    suspend fun getAuthors(call: ApplicationCall) {
        val authors = interactor.getAuthors()
        state.authors = authors
        call.respond(authors)
    }

    suspend fun loginAuthor(call: ApplicationCall) {
        val username = call.getQuery<String>("username")
        val author = state.authors.firstOrNull { it.username == username }
        author?.let{ state.loggedInAuthor = author }
        call.respond(author.toString())
    }

    suspend fun getAuthor(call: ApplicationCall) {
        val authorId = call.getPath<String>("id").toInt()
        val author = interactor.getAuthor(authorId)
        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)
        call.respond(author)
    }

    suspend fun createAuthor(call: ApplicationCall) {
        val article = call.getBodyParam<Author>("body")
        if (false) httpException(HttpStatusCode.BadRequest)
        interactor.createAuthor(article)
        call.respond(article)
    }

    suspend fun updateAuthor(call: ApplicationCall) {
        val body = call.getBodyParam<Author>("body")
        interactor.updateAuthor(body)

        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)

        call.respond("updateAuthor")
    }

    suspend fun deleteAuthor(call: ApplicationCall) {
        val id = call.getPath<String>("id").toInt()
        interactor.deleteAuthor(id)
        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)

        call.respond("deleteAuthor")
    }

}