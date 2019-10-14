package hu.bme.koltin.mdt72t

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.thymeleaf.Thymeleaf
import io.ktor.thymeleaf.ThymeleafContent
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import com.fasterxml.jackson.databind.*
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.jackson.*
import io.ktor.auth.*
import io.ktor.swagger.experimental.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

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
            call.respondText("myHELLO WORLD!", contentType = ContentType.Text.Plain)
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
            exception<HttpException> {  cause ->
                call.respond(cause.code, cause.description)
            }
        }

        MyForumServer().apply {
            registerForum()
            registerAuthor()
        }
    }
}

private fun hikari(): HikariDataSource {
    val config = HikariConfig()
    config.driverClassName = "org.h2.Driver"
    config.jdbcUrl = "jdbc:h2:mem:test"
    config.maximumPoolSize = 3
    config.isAutoCommit = true
    config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
    config.validate()
    return HikariDataSource(config)
}

fun initDb(){
    Database.connect(hikari())
    transaction {
        SchemaUtils.create(AuthorTable,ArticleTable)
        commit()
    }
}

data class ThymeleafUser(val id: Int, val name: String)

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

