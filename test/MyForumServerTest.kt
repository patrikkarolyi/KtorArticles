package hu.bme.koltin.mdt72t

import io.ktor.config.*
import io.ktor.http.*
import io.ktor.server.testing.*
import util.Json
import kotlin.test.*

class SwaggerRoutesTest {
    /**
     * @see MyForumServer.getArticles
     */
    @Test
    fun testGetArticles() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/forum/article") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see MyForumServer.createArticle
     */
    @Test
    fun testCreateArticle() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "/forum/article") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see MyForumServer.getArticle
     */
    @Test
    fun testGetArticle() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/forum/article/{articleId}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see MyForumServer.createAuthor
     */
    @Test
    fun testCreateAuthor() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Post, "") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see MyForumServer.loginUser
     */
    @Test
    fun testLoginUser() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/login") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see MyForumServer.getAuthorById
     */
    @Test
    fun testGetAuthorById() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Get, "/{authorId}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see MyForumServer.updateUser
     */
    @Test
    fun testUpdateUser() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Put, "/{authorId}") {
                // @TODO: Your body here
                setBodyJson(mapOf<String, Any?>())
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    /**
     * @see MyForumServer.deleteAuthor
     */
    @Test
    fun testDeleteAuthor() {
        withTestApplication {
            // @TODO: Adjust path as required
            handleRequest(HttpMethod.Delete, "/{authorId}") {
            }.apply {
                // @TODO: Your test here
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    fun <R> withTestApplication(test: TestApplicationEngine.() -> R): R {
        return withApplication(createTestEnvironment()) {
            (environment.config as MapApplicationConfig).apply {
                put("jwt.secret", "TODO-change-this-supersecret-or-use-SECRET-env")
            }
            application.module()
            test()
        }
    }

    fun TestApplicationRequest.setBodyJson(value: Any?) = setBody(Json.stringify(value))
}
