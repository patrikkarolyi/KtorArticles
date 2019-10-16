package hu.bme.koltin.mdt72t

import io.ktor.swagger.experimental.verifyParam
import org.jetbrains.exposed.sql.Table
import java.util.*

data class Article(
    val id: Int,
    val authorId: Int,
    val title: String,
    val publicationDate: Long,
    val topic: String
) {
    init {
        topic.verifyParam("topic") { it in setOf("Technology", "Biology", "Physics", "Matemathics", "Sport") }
    }
}

object ArticleTable : Table() {
    val id = integer("id").primaryKey().autoIncrement()
    val authorId = integer("authorid")
    val title = varchar("name", 255)
    val topic = varchar("topic", 255)
    val publicationDate = long("dateCreated")
}

data class Author(
    val id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String
)

object AuthorTable : Table("authors") {
    val id = integer("id").primaryKey().autoIncrement()
    val username = varchar("username", 255)
    val firstname = varchar("firstname", 255)
    val lastname = varchar("lastname", 255)
    val email = varchar("email", 255)
}

// Synthetic class name
class Responses(
)
