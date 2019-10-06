package hu.bme.koltin.mdt72t

import hu.bme.koltin.mdt72t.routes.AuthorRoute
import hu.bme.koltin.mdt72t.routes.ForumRoute
import io.ktor.application.call
import io.ktor.routing.*

/**
 * Root of routing
 */
class MyForumServer {

}
    fun Routing.registerForum() {
        val fr = ForumRoute()
        route("/forum/article") {
            get("/") { fr.getArticles(call) }
            post("/") { fr.createArticle(call) }
            get("/{articleId}") { fr.getArticle(call) }
        }
    }

    fun Routing.registerAuthor() {
        val ar = AuthorRoute()
        route("/author") {
            post("/") { ar.getAuthors(call) }
            get("/login") { ar.loginAuthor(call) }
            get("/{authorId}") { ar.getAuthor(call) }
            put("/{authorId}") { ar.updateAuthor(call) }
            delete("/{authorId}") { ar.deleteAuthor(call) }
        }
    }
