package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Article
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.swagger.experimental.getBodyParam
import io.ktor.swagger.experimental.getPath
import io.ktor.swagger.experimental.httpException
import java.lang.StringBuilder
import java.util.*

class ForumRoute(){

    val articles = mutableListOf<Article>()


    suspend fun getArticles(call: ApplicationCall) {
        val builder = StringBuilder()
        for(article in articles){
            builder
                .append(article.title)
                .append("/n")
        }
        call.respond(builder.toString())
    }

    suspend fun createArticle(call: ApplicationCall) {
        val body = call.getBodyParam<Article>("body")
        if (false) httpException(HttpStatusCode.BadRequest)
        articles.add(body)
        call.respond(
            Article(
                id = 0,
                authorId = 0,
                title = "article csucs",
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
                title = "a legujabb hirek.",
                publicationDate = Date(),
                topic = "myforumserver.kt"
            )
        )
    }

}