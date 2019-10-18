package hu.bme.koltin.mdt72t

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.*
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.*
import io.ktor.content.TextContent
import io.ktor.http.ContentType
import io.ktor.http.ContentType.Application.Json

/**
 * MyForum Client
 * Some brief description.
 */
class MyForumClient(
    val endpoint: String,
    val client: HttpClient = HttpClient(Apache))
{

    /**
     * List all articles
     * @return successful operation
     */
    suspend fun getArticles(): String
    {
        return client.get<String>("$endpoint/article")
    }

    /**
     * Create an article
     * @param body article for creation
     * @return successful operation
     */
    suspend fun createArticle(article: Article) {
        return client.post("$endpoint/article") {
            body = article.stringify()
        }
    }

    /**
     * Search article by Id
     * @param articleId none
     * @return successful operation
     */
    suspend fun getArticle(articleId: Int ): String {
        return client.get("$endpoint/article/$articleId")
    }

    /**
     * List all authors
     * @return successful operation
     */
    suspend fun getAuthors(): String
    {
        return client.get<String>("$endpoint/author")
    }

    /**
     * Create an routes.author
     * @param body Created routes.author object
     * @return successful operation
     */
    suspend fun createAuthor(body: Author) {
        return client.post<Unit>("$endpoint/author") {
            this.body = mutableMapOf<String, Any?>().apply { this["body"] = body }
        }
    }

    /**
     * Logs routes.author into the system
     * @param username The user name for login
     * @return successful operation
     */
    suspend fun loginAuthor(username: String): String {
        return client.post<String>("$endpoint/author/login") {
            this.body = mutableMapOf<String, Any?>().apply { this["body"] = username }
        }
    }

    /**
     * Get routes.author by user name
     * @param authorId none
     * @return successful operation
     */
    suspend fun getAuthor(authorId: Int): Author {
        return client.get<Author>("$endpoint/routes.author/$authorId") {
        }
    }

    /**
     * Updated routes.author
     * @param authorId none
     * @param body Updated user object
     * @return OK
     */
    suspend fun updateAuthor(authorId: Int, body: Author): String {
        return client.put<String>("$endpoint/author/$authorId") {
            this.body = mutableMapOf<String, Any?>().apply { this["body"] = body }
        }
    }

    /**
     * Delete routes.author
     * @param authorId none
     * @return OK
     */
    suspend fun deleteAuthor(authorId: Int): String {
        return client.delete<String>("$endpoint/author/$authorId") {
        }
    }
}

fun Article.stringify(): TextContent {
    val json = jacksonObjectMapper()
    return TextContent(json.writeValueAsString(this), contentType = ContentType.Application.Json)
}
