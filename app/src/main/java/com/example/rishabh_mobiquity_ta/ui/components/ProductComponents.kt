package com.example.rishabh_mobiquity_ta.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.rishabh_mobiquity_ta.R
import com.example.rishabh_mobiquity_ta.ui.data.AppConstants
import com.example.rishabh_mobiquity_ta.ui.theme.BackgroundColor
import com.example.rishabh_mobiquity_ta.ui.theme.DarkBlackColor
import com.example.rishabh_mobiquity_ta.ui.theme.RedColor


@Composable
fun ProductComponents(
    image_url: String?,
    product_name: String?,
    product_price: String?,
    navHostController: NavController
) {
    Log.d("main", "MovieDetailScreen: $product_name")
    Column(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor)) {
        ShoppingHeader(icon = R.drawable.back, product_name!!){
            navHostController.popBackStack()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
        ) {
            SpacerHeight(15.dp)
            Image(
                painter = rememberAsyncImagePainter(
                    AppConstants.APP_BASE_URL + image_url,
                    placeholder = painterResource(R.drawable.placeholder),
                    error = painterResource(R.drawable.error)
                ),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally).size(300.dp),
            )
            SpacerHeight()
            Text(
                text = product_name!!,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W900,
                    color = DarkBlackColor
                ),
                textAlign = TextAlign.Center
            )
            SpacerHeight(15.dp)
            Text(
                text = product_price!!,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W600,
                    color = RedColor
                ),
                textAlign = TextAlign.Center
            )

        }
    }
}