package hu.bme.koltin.mdt72t.client

import hu.bme.koltin.mdt72t.Article
import hu.bme.koltin.mdt72t.Author
import hu.bme.koltin.mdt72t.MyForumClient
import kotlinx.coroutines.runBlocking


fun main() : Unit = runBlocking {
    val client = MyForumClient(endpoint = "http://127.0.0.1:8080")


    return@runBlocking

    client.createArticle(demoArticle)
    val articles = client.getArticles()
    val article = client.getArticle(1)


    val authors = client.getAuthors()
    val author = client.getAuthor(1)
    val createdId = client.createAuthor(demoAuthor)
    val updatedId = client.updateAuthor(demoAuthor)
    val deletedId = client.deleteAuthor(1)
    val loggedInAuthor = client.loginAuthor(demoAuthor.username)



}


val demoArticle = Article(
    id = 0,
    authorId = 0,
    title = "Title",
    topic = "Sport",
    publicationDate = 123L
)

val demoAuthor = Author(
    id = 1,
    username = "a",
    firstname = "a",
    lastname = "a",
    email = "a"
)




