package hu.bme.koltin.mdt72t.db

import hu.bme.koltin.mdt72t.Author
import hu.bme.koltin.mdt72t.AuthorTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class AuthorInteractor {

    fun getAuthors(): List<Author> = transaction {
        AuthorTable.selectAll().getAuthorModelList()
    }


    fun getAuthor(id: Int) = transaction {
        AuthorTable.select { AuthorTable.id.eq(id) }.getAuthorModelList().firstOrNull()
    }


    fun createAuthor(author: Author) = transaction {
        val id = AuthorTable.insert {
            it[email] = author.email
            it[firstname] = author.firstname
            it[lastname] = author.lastname
            it[username] = author.username
        }
        commit()
        id.generatedKey?.toInt()
    }


    fun updateAuthor(author: Author) = transaction {
        val updatedItemNumber = AuthorTable.update({ AuthorTable.id.eq(author.id) }) {
            it[email] = author.email
            it[firstname] = author.firstname
            it[lastname] = author.lastname
            it[username] = author.username
        }
        commit()
        updatedItemNumber
    }


    fun deleteAuthor(id: Int) = transaction {
        val deletedItemNumber = AuthorTable.deleteWhere { AuthorTable.id.eq(id) }
        commit()
        deletedItemNumber
    }


}

private fun Query.getAuthorModelList(): List<Author> {
    return this.map {
        Author(
            id = it[AuthorTable.id],
            email = it[AuthorTable.email],
            username = it[AuthorTable.username],
            firstname = it[AuthorTable.firstname],
            lastname = it[AuthorTable.lastname]
        )
    }
}