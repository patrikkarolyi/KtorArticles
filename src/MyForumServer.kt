package hu.bme.koltin.mdt72t

import hu.bme.koltin.mdt72t.routes.AuthorPresenter
import hu.bme.koltin.mdt72t.routes.ArticlePresenter
import io.ktor.application.call
import io.ktor.routing.*

/**
 * Root of routing
 */
class MyForumServer {

}
    fun Routing.registerForum() {
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
