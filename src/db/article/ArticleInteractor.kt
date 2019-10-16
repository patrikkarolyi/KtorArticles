package hu.bme.koltin.mdt72t.db.article

import hu.bme.koltin.mdt72t.Article
import hu.bme.koltin.mdt72t.ArticleTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ArticleInteractor {

    fun getArticles(): List<Article> =
        transaction {
            ArticleTable.selectAll().map {
                Article(
                    id = it[ArticleTable.id],
                    authorId = it[ArticleTable.authorId],
                    title = it[ArticleTable.title],
                    topic = it[ArticleTable.topic],
                    publicationDate = it[ArticleTable.publicationDate]
                )
            }
        }


    fun getArticle(id: Int) =
        transaction {
            ArticleTable.select { ArticleTable.id.eq(id) }
        }


    fun createArticle(article: Article) {
        transaction {
            ArticleTable.insert {
                it[authorId] = article.authorId
                it[title] = article.title
                it[topic] = article.topic
                it[publicationDate] = article.publicationDate
            }
            commit()
        }
    }

    fun updateArticle(article: Article) {
        transaction {
            ArticleTable.update({ ArticleTable.id.eq(article.id) }) {
                it[authorId] = article.authorId
                it[title] = article.title
                it[topic] = article.topic
                it[publicationDate] = article.publicationDate
            }
            commit()
        }
    }

    fun deleteArticle(id: Int) {
        transaction {
            ArticleTable.deleteWhere { ArticleTable.id.eq(id) }
            commit()
        }
    }

}