package hu.bme.koltin.mdt72t.db

import hu.bme.koltin.mdt72t.ArticleTable
import hu.bme.koltin.mdt72t.AuthorTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initDb() {
    val user = "root"
    val password = ""
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
