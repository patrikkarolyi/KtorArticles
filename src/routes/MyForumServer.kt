package hu.bme.koltin.mdt72t

import hu.bme.koltin.mdt72t.routes.ArticlePresenter
import hu.bme.koltin.mdt72t.routes.AuthorPresenter
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.thymeleaf.ThymeleafContent

/**
 * Root of routing
 */
class MyForumServer {

    fun Routing.registerArticle() {
        val fr = ArticlePresenter()
        route("/article") {

            get("/") { fr.getArticles(call) }
            post("/") { fr.createArticle(call) }

            get("/{articleId}") { fr.getArticle(call) }
        }
    }

    fun Routing.registerAuthor() {
        val ar = AuthorPresenter()
        route("/author") {

            get("/") { ar.getAuthors(call) }
            post("/") { ar.createAuthor(call) }

            get("/login") { ar.loginAuthor(call) }

            get("/{authorId}") { ar.getAuthor(call) }
            put("/{authorId}") { ar.updateAuthor(call) }
            delete("/{authorId}") { ar.deleteAuthor(call) }

        }
    }

    fun Routing.registerDemo() {
        route("") {
            get("/") {
                call.respondText("Roadmap", contentType = ContentType.Text.Plain)
            }

            get("/html-thymeleaf") {
                call.respond(ThymeleafContent("index", mapOf("user" to ThymeleafUser(1, "user1"))))
            }

            get("/json/gson") {
                call.respond(mapOf("hello" to "world"))
            }

            get("/json/jackson") {
                call.respond(mapOf("hello" to "world"))
            }

        }
    }

}
