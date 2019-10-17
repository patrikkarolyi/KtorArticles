package hu.bme.koltin.mdt72t

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.swagger.experimental.HttpException
import io.ktor.thymeleaf.Thymeleaf
import io.ktor.thymeleaf.ThymeleafContent
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
fun Application.module() {

    initDb()

    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }

    install(ContentNegotiation) {
        gson {
        }

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val client = HttpClient(Apache) {
    }

    install(Authentication) {
    }

    routing {
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

        install(StatusPages) {

            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }
            exception<HttpException> { cause ->
                call.respond(cause.code, cause.description)
            }
        }

        MyForumServer().apply {
            registerForum()
            registerAuthor()
        }
    }
}

fun initDb() {
    val user = "root"
    val password =""
    val address = "localhost"
    val port = "3306"
    val database = "ktordb"
    val unicode = "useUnicode=true"
    val timezone = "serverTimezone=UTC"
    val ssl = "useSSL=false"

    val url = "jdbc:mysql://$user:$password@$address:$port/$database?$unicode&$timezone&$ssl"
    val driver = "com.mysql.cj.jdbc.Driver"
    Database.connect(url = url, driver = driver, user = "root", password = "")

    transaction {
        SchemaUtils.create(AuthorTable, ArticleTable)
        commit()
    }
}

data class ThymeleafUser(val id: Int, val name: String)

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

