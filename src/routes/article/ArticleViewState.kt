package hu.bme.koltin.mdt72t.routes.article

import hu.bme.koltin.mdt72t.Article

data class ArticleViewState(
    var articles : List<Article> = listOf()
)