package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Article
import hu.bme.koltin.mdt72t.db.article.ArticleInteractor
import hu.bme.koltin.mdt72t.routes.article.ArticleViewState
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.request.receiveOrNull
import io.ktor.response.respond
import util.getBodyParam
import util.getPath
import util.httpException

class ArticlePresenter {

    val interactor = ArticleInteractor()
    val state = ArticleViewState()

    suspend fun getArticles(call: ApplicationCall) {
        val articles = interactor.getArticles()
        state.articles = articles
        call.respond(articles)
    }

    suspend fun getArticle(call: ApplicationCall) {
        val id = call.parameters["articleId"]?.toInt() ?: return call.respond(HttpStatusCode.BadRequest)
        val article = interactor.getArticle(id) ?:  return call.respond(HttpStatusCode.NotFound)
        call.respond(article)
    }

    suspend fun createArticle(call: ApplicationCall) {
        val article = call.receiveOrNull<Article>() ?: return call.respond(HttpStatusCode.BadRequest)
        val generatedId = interactor.createArticle(article)
        call.respond("article created with $generatedId id.")
    }



}


