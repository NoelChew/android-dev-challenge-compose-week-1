/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.data.initialPuppies
import com.example.androiddevchallenge.puppy.Puppy
import com.example.androiddevchallenge.puppy.PuppyList
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(onPuppyClicked)
            }
        }
    }

    var onPuppyClicked: ((puppy: Puppy) -> Unit) = {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        intent.putExtra("puppyJson", Gson().toJson(it))
        this.startActivity(intent)
    }
}

// Start building your app here!
@ExperimentalAnimationApi
@Composable
fun MyApp(navigateToPuppy: (Puppy) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.fillMaxHeight()) {
            Text(
                text = "Puppy Adoption App",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(8.dp))
            )
            PuppyList(
                initialPuppies,
                Modifier.fillMaxHeight(),
                navigateToPuppy
            )
        }
    }
}
