package com.example.tmdb_compose.domain

enum class Category {
    POPULAR,
    TOP_RATED,
    UP_COMPING,
}

fun getCategoryName(category: Category): String {
    return when (category) {
        Category.POPULAR -> "Popular"
        Category.UP_COMPING -> "Up Coming"
        Category.TOP_RATED -> "Top Rated"
    }

}
