package com.example.rishabh_mobiquity_ta.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.rishabh_mobiquity_ta.ui.navigation.AppNavigationGraph
import com.example.rishabh_mobiquity_ta.ui.theme.Rishabh_Mobiquity_TATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Rishabh_Mobiquity_TATheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)
                {
                    ShoppingAppEntryPoint()
                }
            }
        }
    }
}

@Composable
fun ShoppingAppEntryPoint() {
    AppNavigationGraph()
}

