package hu.bme.koltin.mdt72t.db

import hu.bme.koltin.mdt72t.AuthorTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class AuthorInteractor{

    fun getArticles(){
        transaction {
            val a = AuthorTable.selectAll()
            a.forEach {
                println(it)
            }
        }
    }

    fun getArticle(id : Int){
        transaction {
            val author = AuthorTable.select{ AuthorTable.id.eq(id) }
            println(author)
        }
    }

    fun createArticle(){
        transaction {
            AuthorTable.insert{
                it[email]="papaja"
                it[firstname]="cocnut"
                it[lastname]="hibiscus"
                it[username]="csalamádé"
            }
            commit()
        }
    }


}
