package routes.author

import hu.bme.koltin.mdt72t.Author

data class AuthorViewState(
    var loggedInAuthor: Author = Author(
        id = 0,
        username = "Anonym",
        firstname = "John",
        lastname = "Doe",
        email = "none"
    ),
    var authors: List<Author> = listOf()
)

