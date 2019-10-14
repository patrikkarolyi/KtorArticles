package hu.bme.koltin.mdt72t.db

import hu.bme.koltin.mdt72t.AuthorTable
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class AuthorInteractor{

    fun getArticles(){
        transaction {
            val a = AuthorTable.selectAll()
            a.forEach {
                println(it[AuthorTable.email] + "sikerült")
            }
        }
    }


}
