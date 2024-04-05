package com.example.testapplication.testdoubles

import com.example.testapplication.data.data_source.model.ApiResponseItem
import com.example.testapplication.data.model.CatItem
import com.example.testapplication.data.model.Image
import java.lang.RuntimeException

val sampleCatsList = listOf(
    CatItem(text = "Cat 1", image = Image("", "")),
    CatItem(text = "Cat 2", image = Image("", "")),
    CatItem(text = "Cat 3", image = Image("", "")),
    CatItem(text = "Cat 4", image = Image("", "")),
    CatItem(text = "Cat 5", image = Image("", "")),
)

val sampleCatsList2 = listOf(
    CatItem(text = "Cat 6", image = Image("", "")),
    CatItem(text = "Cat 7", image = Image("", "")),
)

val sampleCatsList3 = listOf(
    CatItem(text = "Cat 8", image = Image("", "")),
)

fun getThrowable(): List<ApiResponseItem> {
    throw RuntimeException("broken")
}