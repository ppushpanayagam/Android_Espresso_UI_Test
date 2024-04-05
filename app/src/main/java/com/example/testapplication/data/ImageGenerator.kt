package com.example.testapplication.data

import com.example.testapplication.data.model.Image
import com.example.testapplication.data.model.catPictures
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class ImageGenerator @Inject constructor() {
     fun get(): Image = catPictures[Random.nextInt(catPictures.size)]
}