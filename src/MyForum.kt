package hu.bme.koltin.mdt72t

import io.ktor.swagger.experimental.verifyParam
import java.util.*

data class Article(
    val id: Long,
    val authorId: Long,
    val title: String,
    val publicationDate: Date,
    val topic: String
) {
    init {
        topic.verifyParam("topic") { it in setOf("Technology", "Biology", "Physics", "Matemathics", "Sport") }
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
