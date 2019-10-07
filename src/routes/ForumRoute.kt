package hu.bme.koltin.mdt72t.routes

import hu.bme.koltin.mdt72t.Article
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.swagger.experimental.getBodyParam
import io.ktor.swagger.experimental.getPath
import io.ktor.swagger.experimental.httpException
import io.ktor.thymeleaf.ThymeleafContent
import java.util.*

class ForumRoute() {

    private val articles = mutableListOf(
        Article(
            id = 0,
            authorId = 0,
            title = "For Braves and Cardinals, the Managers Are Company Men",
            content = "Brian Snitker has been in Atlanta’s system since the Carter administration, and Mike Shildt has never worked for another pro baseball organization — rarities in the modern game.",
            publicationDate = Date(),
            topic = "Sport"
        ),
        Article(
            id = 1,
            authorId = 0,
            title = "N.F.L. Week 5 Predictions: Our Picks Against the Spread",
            content = "Red-hot backup quarterbacks will battle in Carolina, Patrick Mahomes gets a spotlight in prime time, and something has to give when hapless Cincinnati hosts winless Arizona.",
            publicationDate = Date(),
            topic = "Sport"
        ),
        Article(
            id = 0,
            authorId = 0,
            title = "For Braves and Cardinals, the Managers Are Company Men",
            content = "Brian Snitker has been in Atlanta’s system since the Carter administration, and Mike Shildt has never worked for another pro baseball organization — rarities in the modern game.",
            publicationDate = Date(),
            topic = "Sport"
        )
        )

    suspend fun getArticles(call: ApplicationCall) {
        val content = mapOf("articles" to articles)
        call.respond(ThymeleafContent("articles", content))
    }

    suspend fun createArticle(call: ApplicationCall) {
        val body = call.getBodyParam<Article>("body")
        if (false) httpException(HttpStatusCode.BadRequest)
        articles.add(body)
        call.respond(body)
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
                content = "content",
                publicationDate = Date(),
                topic = "myforumserver.kt"
            )
        )
    }

    suspend fun toCreateArticle(call: ApplicationCall) {
        val content = mapOf("articles" to articles)
        call.respond(ThymeleafContent("newarticle", content))
    }

}