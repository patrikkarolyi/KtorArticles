package hu.bme.koltin.mdt72t

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.content.TextContent
import io.ktor.http.ContentType

/**
 * MyForum Client
 * Some brief description.
 */
class MyForumClient(
    val endpoint: String,
    val client: HttpClient = HttpClient(Apache)
) {

    /**
     * List all articles
     * @return successful operation
     */
    suspend fun getArticles(): String {
        return client.get<String>("$endpoint/article")
    }

    /**
     * Create an article
     * @param body article for creation
     * @return successful operation
     */
    suspend fun createArticle(article: Article): String = client.post("$endpoint/article") {
        body = article.stringify()
    }


    /**
     * Search article by Id
     * @param articleId none
     * @return successful operation
     */
    suspend fun getArticle(articleId: Int): String {
        return client.get("$endpoint/article/$articleId")
    }

    /**
     * List all authors
     * @return successful operation
     */
    suspend fun getAuthors(): String {
        return client.get<String>("$endpoint/author")
    }

    /**
     * Create an routes.author
     * @param body Created routes.author object
     * @return successful operation
     */
    suspend fun createAuthor(author: Author): String = client.post("$endpoint/author") {
        body = author.stringify()
    }


    /**
     * Get routes.author by user name
     * @param authorId none
     * @return successful operation
     */
    suspend fun getAuthor(authorId: Int): String {
        return client.get("$endpoint/author/$authorId")
    }

    /**
     * Logs routes.author into the system
     * @param username The user name for login
     * @return successful operation
     */
    suspend fun loginAuthor(username: String): String {
        return client.get("$endpoint/author/login/$username")
    }

    /**
     * Updated routes.author
     * @param authorId none
     * @param body Updated user object
     * @return OK
     */
    suspend fun updateAuthor(author: Author): String = client.put("$endpoint/author") {
        body = author.stringify()
    }

    /**
     * Delete routes.author
     * @param authorId none
     * @return OK
     */
    suspend fun deleteAuthor(authorId: Int): String {
        return client.delete("$endpoint/author/$authorId")
    }
}

fun Article.stringify(): TextContent {
    val json = jacksonObjectMapper()
    return TextContent(json.writeValueAsString(this), contentType = ContentType.Application.Json)
}

fun Author.stringify(): TextContent {
    val json = jacksonObjectMapper()
    return TextContent(json.writeValueAsString(this), contentType = ContentType.Application.Json)
}
