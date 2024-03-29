package com.example.rishabh_mobiquity_ta.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.rishabh_mobiquity_ta.common.Network
import com.example.rishabh_mobiquity_ta.ui.components.NoInternetConnection
import com.example.rishabh_mobiquity_ta.ui.navigation.AppNavigationGraph
import com.example.rishabh_mobiquity_ta.ui.theme.Rishabh_Mobiquity_TATheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Rishabh_Mobiquity_TATheme(darkTheme = false) {

                var isSelected by remember { mutableStateOf(false) }

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background)

                {
                    if(isSelected || !isSelected){
                        if (Network.checkConnectivity(this@MainActivity))
                            ShoppingAppEntryPoint()
                        else
                            NoInternetConnection(){
                               isSelected = !isSelected
                            }
                    }

                }
            }
        }
    }
}

@Composable
fun ShoppingAppEntryPoint() {
    AppNavigationGraph()
}

