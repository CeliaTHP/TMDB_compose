package com.example.tmdb_compose.domain

class CategoryInfos(
    val categoryTitle: String,
    val category: Category
)

enum class Category {
    POPULAR,
    TOP_RATED,
    UP_COMPING
}