package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Article
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.swagger.experimental.getBodyParam
import io.ktor.swagger.experimental.getPath
import io.ktor.swagger.experimental.httpException
import java.util.*

class ForumRoute(){

    suspend fun getArticles(call: ApplicationCall) {
        call.respond("Ez valóban működik")
    }

    suspend fun createArticle(call: ApplicationCall) {
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

    suspend fun getArticle(call: ApplicationCall) {
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