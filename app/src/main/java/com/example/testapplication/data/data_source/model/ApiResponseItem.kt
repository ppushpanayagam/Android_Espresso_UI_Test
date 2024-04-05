package com.example.testapplication.data.data_source.model

data class ApiResponseItem(
    val status: Status? = null,
    val _id: String? = null,
    val user: String? = null,
    val text: String? = null,
    val __v: Int = 0,
    val source: String? = null,
    val updatedAt: String? = null,
    val type: String? = null,
    val createdAt: String? = null,
    val deleted: Boolean = false,
    val used: Boolean = false
)

data class Status(val verified: Boolean, val sentCount: Int)