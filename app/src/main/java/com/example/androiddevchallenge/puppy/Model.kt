package com.example.androiddevchallenge.puppy

import androidx.compose.runtime.Immutable


@Immutable
data class Puppy(
    val name: String,
    val breed: String,
    val description: String,
    val image: Int
)
