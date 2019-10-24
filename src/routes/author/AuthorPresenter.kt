package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Article
import hu.bme.koltin.mdt72t.Author
import hu.bme.koltin.mdt72t.db.AuthorInteractor
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveOrNull
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
        val username = call.parameters["username"].toString()
        val author = interactor.getAuthors().firstOrNull{it.username == username} ?:  return call.respond(HttpStatusCode.NotFound)
        state.loggedInAuthor = author
        call.respond(author)
    }

    suspend fun getAuthor(call: ApplicationCall) {
        val id = call.parameters["authorId"]?.toInt()  ?: return call.respond(HttpStatusCode.BadRequest)
        val author = interactor.getAuthor(id) ?:  return call.respond(HttpStatusCode.NotFound)
        call.respond(author)
    }

    suspend fun createAuthor(call: ApplicationCall) {
        val author = call.receiveOrNull<Author>() ?: return call.respond(HttpStatusCode.BadRequest)
        val generatedId = interactor.createAuthor(author)
        call.respond("author created with $generatedId id.")
    }

    suspend fun updateAuthor(call: ApplicationCall) {
        val author = call.receiveOrNull<Author>() ?: return call.respond(HttpStatusCode.BadRequest)
        val generatedId = interactor.updateAuthor(author) ?: httpException(HttpStatusCode.NotFound)
        call.respond("author updated with $generatedId id.")
    }

    suspend fun deleteAuthor(call: ApplicationCall) {
        val id =  call.parameters["authorId"]?.toInt()  ?: return call.respond(HttpStatusCode.BadRequest)
        interactor.deleteAuthor(id) ?: httpException(HttpStatusCode.NotFound)
        call.respond("author deleted with $id id.")
    }

}