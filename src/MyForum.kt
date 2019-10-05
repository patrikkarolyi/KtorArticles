package hu.bme.koltin.mdt72t

import java.util.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.swagger.experimental.*

data class Article(
    val id: Long,
    val authorId: Long,
    val publicationDate: Date,
    val topic: String
) {
    init {
        topic.verifyParam("topic") { it in setOf("Technology", "Biology", "Physics", "Matemathics","Sport") }
    }
}

data class Author(
    val id: Long,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

// Synthetic class name
class Responses(
)
