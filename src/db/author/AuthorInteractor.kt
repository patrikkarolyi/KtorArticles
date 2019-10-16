package hu.bme.koltin.mdt72t.db

import hu.bme.koltin.mdt72t.Author
import hu.bme.koltin.mdt72t.AuthorTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class AuthorInteractor {

    fun getAuthors(): List<Author> =
        transaction {
            AuthorTable.selectAll().map {
                Author(
                    id = it[AuthorTable.id],
                    username = it[AuthorTable.username],
                    firstname = it[AuthorTable.firstname],
                    lastname = it[AuthorTable.lastname],
                    email = it[AuthorTable.email]
                )
            }
        }


    fun getAuthor(id: Int) =
        transaction {
            AuthorTable.select { AuthorTable.id.eq(id) }
        }


    fun createAuthor(author: Author) {
        transaction {
            AuthorTable.insert {
                it[email] = author.email
                it[firstname] = author.firstname
                it[lastname] = author.lastname
                it[username] = author.username
            }
            commit()
        }
    }

    fun updateAuthor(author: Author) {
        transaction {
            AuthorTable.update({ AuthorTable.id.eq(author.id) }) {
                it[email] = author.email
                it[firstname] = author.firstname
                it[lastname] = author.lastname
                it[username] = author.username
            }
            commit()
        }
    }

    fun deleteAuthor(id: Int) {
        transaction {
            AuthorTable.deleteWhere { AuthorTable.id.eq(id) }
            commit()
        }
    }

}
