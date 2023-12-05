package com.example.movieviews.data.models

data class Review(
    var author: String? = null,
    var author_details: AuthorDetails? = null,
    var content: String? = null,
    var created_at: String? = null,
    var id: String? = null,
    var updated_at: String? = null,
    var url: String? = null
)