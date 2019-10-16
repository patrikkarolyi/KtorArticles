package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Article
import hu.bme.koltin.mdt72t.db.article.ArticleInteractor
import hu.bme.koltin.mdt72t.routes.article.ArticleViewState
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.swagger.experimental.getBodyParam
import io.ktor.swagger.experimental.getPath
import io.ktor.swagger.experimental.httpException
import java.util.*

class ArticlePresenter {

    val interactor = ArticleInteractor()
    val state = ArticleViewState()

    suspend fun getArticles(call: ApplicationCall) {
        val articles = interactor.getArticles()
        state.articles = articles
        call.respond(articles)
    }

    suspend fun getArticle(call: ApplicationCall) {
        val id = call.getPath<String>("articleId").toInt()
        val article = interactor.getArticle(id)

        if (false) httpException(HttpStatusCode.BadRequest)
        if (false) httpException(HttpStatusCode.NotFound)

        call.respond(article)
    }

    suspend fun createArticle(call: ApplicationCall) {
        val article = call.getBodyParam<Article>("body")
        interactor.createArticle(article)

        if (false) httpException(HttpStatusCode.BadRequest)

        call.respond(article)
    }



}