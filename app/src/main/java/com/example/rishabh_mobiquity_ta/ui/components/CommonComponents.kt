package com.example.rishabh_mobiquity_ta.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rishabh_mobiquity_ta.R
import com.example.rishabh_mobiquity_ta.ui.theme.RedColor

@Composable
fun ShoppingHeader(
    @DrawableRes icon: Int,
    text_str: String,
    onGettingClick:() -> Unit){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(RedColor)){

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp, horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                AppIconButton(icon){onGettingClick()}
                SpacerWidth(10.dp)
                Text(text = text_str, style = TextStyle(
                    fontSize = 19.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.White
                ))
            }
            AppIconButton(icon = R.drawable.search){}
        }
    }
}

@Composable
fun AppIconButton(
    @DrawableRes icon: Int,
    tint: Color = Color.White,
    modifier: Modifier = Modifier,
    onGettingClick:() -> Unit
){
    IconButton(onClick = {onGettingClick()}, modifier = modifier.size(28.dp)) {
        Icon(painter = painterResource(id = icon), contentDescription = "", tint = tint)
    }
}

@Composable
fun SpacerWidth(
    width: Dp = 5.dp
){
    Spacer(modifier = Modifier.width(width))
}

@Composable
fun SpacerHeight(
    height: Dp = 5.dp
){
    Spacer(modifier = Modifier.height(height))
}