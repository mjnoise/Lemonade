package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.When

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeWithImagesAndText(modifier: Modifier = Modifier) {
    var thisStep by remember {
        mutableStateOf(1)
    }

    when (thisStep) {
        1 -> {
            ImagesAndText(
                modifier = modifier,
                text = "Tap the lemon tree to select a lemon",
                imageResource = painterResource(id = R.drawable.lemon_tree),
                imageContent = "Lemon Tree",
                onImageClick = {
                    thisStep = 2
                }
            )
        }
        else -> ""
    }
}

@Composable
fun ImagesAndText(
    modifier: Modifier = Modifier,
    text: String,
    imageResource: Painter,
    imageContent: String,
    onImageClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = text, fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(25.dp))
        Image(
            painter = imageResource,
            contentDescription = imageContent,
            modifier = Modifier
                .wrapContentSize()
                .clickable (
                    onClick = onImageClick
                        )
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = Color(105, 205, 216)
                    ),
                    shape = RoundedCornerShape(4.dp)
                )



            )
    }
}

@Preview(
    showBackground = true, showSystemUi = true
)
@Composable
fun LemonadeApp() {
    LemonadeTheme {
        LemonadeWithImagesAndText()
    }
}