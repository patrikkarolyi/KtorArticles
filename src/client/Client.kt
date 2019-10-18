package hu.bme.koltin.mdt72t.client

import hu.bme.koltin.mdt72t.Article
import hu.bme.koltin.mdt72t.Author
import hu.bme.koltin.mdt72t.MyForumClient
import kotlinx.coroutines.runBlocking


fun main() : Unit = runBlocking {
    val client = MyForumClient(endpoint = "http://127.0.0.1:8080")

    val article = client.getArticle(1)


    println(article)
    return@runBlocking

    client.createArticle(demoArticle)
    val articles = client.getArticles()


    val authors = client.getAuthors()
    client.createAuthor(demoAuthor)
    val author = client.getAuthor(0)
    client.loginAuthor(demoAuthor.username)
    client.updateAuthor(demoAuthor.id,demoAuthor)
    client.deleteAuthor(demoAuthor.id)

}


val demoArticle = Article(
    id = 0,
    authorId = 0,
    title = "Title",
    topic = "Sport",
    publicationDate = 123L
)

val demoAuthor = Author(
    id = 0,
    username = "Anonym",
    firstname = "John",
    lastname = "Doe",
    email = "none"
)




