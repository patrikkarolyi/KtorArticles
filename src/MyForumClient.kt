package hu.bme.koltin.mdt72t

import io.ktor.client.*
import io.ktor.client.request.*

/**
 * MyForum Client
 * 
 * Some brief description.
 */
open class MyForumClient(val endpoint: String, val client: HttpClient = HttpClient()) {
    /**
     * List all articles
     * 
     * none
     * 
     * @return successful operation
     */
    suspend fun getArticles(
    ): Responses {
        return client.get<Responses>("$endpoint/forum/article") {
        }
    }

    /**
     * Create an article
     * 
     * none
     * 
     * @param body article for creation
     * 
     * @return successful operation
     */
    suspend fun createArticle(
        body: Article // BODY
    ): Article {
        return client.post<Article>("$endpoint/forum/article") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Search article by Id
     * 
     * none
     * 
     * @param articleId none
     * 
     * @return successful operation
     */
    suspend fun getArticle(
        articleId: String // PATH
    ): Article {
        return client.get<Article>("$endpoint/forum/article/$articleId") {
        }
    }

    /**
     * Create an routes.author
     * 
     * none
     * 
     * @param body Created routes.author object
     * 
     * @return successful operation
     */
    suspend fun createAuthor(
        body: Author // BODY
    ): Unit {
        return client.post<Unit>("$endpoint/routes.author") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Logs routes.author into the system
     * 
     * @param username The user name for login
     * 
     * @return successful operation
     */
    suspend fun loginUser(
        username: String // QUERY
    ): String {
        return client.get<String>("$endpoint/routes.author/login") {
            this.url {
                this.parameters.apply {
                    this.append("username", "$username")
                }
            }
        }
    }

    /**
     * Get routes.author by user name
     * 
     * @param authorId none
     * 
     * @return successful operation
     */
    suspend fun getAuthorById(
        authorId: String // PATH
    ): Author {
        return client.get<Author>("$endpoint/routes.author/$authorId") {
        }
    }

    /**
     * Updated routes.author
     * 
     * none
     * 
     * @param authorId none
     * @param body Updated user object
     * 
     * @return OK
     */
    suspend fun updateUser(
        authorId: String, // PATH
        body: Author // BODY
    ): String {
        return client.put<String>("$endpoint/routes.author/$authorId") {
            this.body = mutableMapOf<String, Any?>().apply {
                this["body"] = body
            }
        }
    }

    /**
     * Delete routes.author
     * 
     * none
     * 
     * @param authorId none
     * 
     * @return OK
     */
    suspend fun deleteAuthor(
        authorId: String // PATH
    ): String {
        return client.delete<String>("$endpoint/routes.author/$authorId") {
        }
    }
}
