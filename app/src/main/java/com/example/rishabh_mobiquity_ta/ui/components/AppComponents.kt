package com.example.rishabh_mobiquity_ta.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rishabh_mobiquity_ta.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.rishabh_mobiquity_ta.ui.data.AppConstants
import com.example.rishabh_mobiquity_ta.ui.data.entity.Products
import com.example.rishabh_mobiquity_ta.ui.data.entity.ShoppingResponse
import com.example.rishabh_mobiquity_ta.ui.navigation.Routes
import com.example.rishabh_mobiquity_ta.ui.theme.*

@Composable
fun Loader(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp),
            color = Teal200
        )
    }
}

@Composable
fun NoInternetConnection(
    onGettingClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No Internet Connection")
        SpacerHeight(20.dp)
        Button(onClick = {onGettingClick()}) {
            Text(text = "Check Again")
        }
    }
}

@Composable
fun ShoppingList(
    response: List<ShoppingResponse>,
    navHostController: NavHostController
){

    var menuState by remember { mutableStateOf(response.get(0).name) }
    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BackgroundColor)){
        Column() {
            ShoppingHeader(icon = R.drawable.menu, "Shopping App"){
            }
            Card (
                modifier = Modifier.fillMaxWidth(),
                elevation = 1.dp
            ){
                Row(
                    modifier = Modifier.horizontalScroll(scrollState)
                ) {
                   for (resp in response){
                       CustomChip(title = resp.name, selected = resp.name == menuState, onValueChange = {
                           menuState = it
                       })
                    }
                }
            }
            LazyVerticalGrid(columns = GridCells.Fixed(2)
            ){
                for (resp in response){
                    if(menuState == resp.name){
                        items(resp.products){
                            ShowProducts(products = it, navHostController){
                                navHostController.navigate(Routes.PRODUCT_DETAILS+"?"
                                        +it.url+"?"
                                        +it.name+"?"
                                        +it.salePrice.currency +" "+ it.salePrice.amount
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CustomChip(
    title: String,
    selected: Boolean,
    onValueChange: (String) -> Unit
){
    TextButton(onClick = {
        onValueChange(title)
    },
        shape = RoundedCornerShape(200.dp),
        elevation = ButtonDefaults.elevation(
            0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if(selected) YellowColor else Color.Transparent
        ),
        modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp)
    ) {
        Text(text = title, style = TextStyle(
            fontSize = 19.sp,
            fontWeight = FontWeight.W600,
            color = if(selected) Color.White else DarkBlackColor
        ))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowProducts(
    products: Products,
    navHostController: NavHostController,
    onGettingClick: () -> Unit
){
    Card(
        modifier = Modifier
            .width(175.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(5.dp),
        onClick = { onGettingClick() }
    ) { 
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
        {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        AppConstants.APP_BASE_URL + products.url,
                        placeholder = painterResource(R.drawable.placeholder),
                        error = painterResource(R.drawable.error)
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
                SpacerHeight()
                Text(
                    text = products.salePrice.currency +" "+ products.salePrice.amount,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = RedColor
                    ),
                    textAlign = TextAlign.Center
                )
                SpacerHeight()
                Text(
                    text = products.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W900,
                        color = DarkBlackColor
                    ),
                    textAlign = TextAlign.Center
                )
                SpacerHeight()
                if(products.description != ""){
                    Text(
                        text = products.description,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.W300,
                            color = LightGrayColor
                        ),
                        textAlign = TextAlign.Center
                    )
                    SpacerHeight()
                }
                Button(onClick = {
                    navHostController.navigate(Routes.PRODUCT_DETAILS+"?"
                            +products.url+"?"
                            +products.name+"?"
                            +products.salePrice.currency +" "+ products.salePrice.amount
                    )
                },
                    modifier = Modifier.width(91.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = YellowColor
                    )
                ) {
                    Text(
                        text = "Details",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W600,
                            color = Color.White
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}



