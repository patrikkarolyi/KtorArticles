package hu.bme.koltin.mdt72t.db.article

import hu.bme.koltin.mdt72t.Article
import hu.bme.koltin.mdt72t.ArticleTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ArticleInteractor {

    fun getArticles(): List<Article> = transaction {
        ArticleTable.selectAll().getArticleModelList()
    }


    fun getArticle(id: Int) = transaction {
        ArticleTable.select { ArticleTable.id.eq(id) }.getArticleModelList().firstOrNull()
    }


    fun createArticle(article: Article) = transaction {
        val id = ArticleTable.insert {
            it[authorId] = article.authorId
            it[title] = article.title
            it[topic] = article.topic
            it[publicationDate] = article.publicationDate
        }
        commit()
        id.generatedKey?.toInt()
    }


    fun updateArticle(article: Article) = transaction {
        val updatedItemNumber = ArticleTable.update({ ArticleTable.id.eq(article.id) }) {
            it[authorId] = article.authorId
            it[title] = article.title
            it[topic] = article.topic
            it[publicationDate] = article.publicationDate
        }
        commit()
        updatedItemNumber
    }


    fun deleteArticle(id: Int) = transaction {
        val deletedItemNumber = ArticleTable.deleteWhere { ArticleTable.id.eq(id) }
        commit()
        deletedItemNumber
    }


}

private fun Query.getArticleModelList(): List<Article> {
    return this.map {
        Article(
            id = it[ArticleTable.id],
            authorId = it[ArticleTable.authorId],
            title = it[ArticleTable.title],
            topic = it[ArticleTable.topic],
            publicationDate = it[ArticleTable.publicationDate]
        )
    }
}
