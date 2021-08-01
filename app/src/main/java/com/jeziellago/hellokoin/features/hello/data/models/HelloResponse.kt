package com.jeziellago.hellokoin.features.hello.data.models

import com.jeziellago.hellokoin.features.hello.domain.entities.Hello
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HelloResponse(
    @Json(name = "message")
    val message: String? = null
)

fun HelloResponse.mapToDomain(): Hello {
    return Hello(message = this.message ?: "")
}

