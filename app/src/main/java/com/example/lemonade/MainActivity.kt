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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

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

    var squeezeNeeded by remember {
        mutableStateOf(0)
    }

    when (thisStep) {
        1 -> {
            ImagesAndText(
                text = stringResource(R.string.lemon_tree_title),
                imageResource = painterResource(id = R.drawable.lemon_tree),
                imageContent = stringResource(R.string.lemon_tree),
                onImageClick = {
                    thisStep = 2
                    squeezeNeeded = (2..4).random()
                }
            )
        }
        2 -> {
            Column (modifier = modifier.wrapContentSize(Alignment.Center)) {
                ImagesAndText(
                    text = stringResource(R.string.lemon_squeeze_title),
                    imageResource = painterResource(id = R.drawable.lemon_squeeze),
                    imageContent = stringResource(R.string.lemon)
                    ) {

                    squeezeNeeded--
                    if (squeezeNeeded == 0) {
                        thisStep++
                    }

                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.squeeze_left) + squeezeNeeded,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }

        3 -> {
            ImagesAndText(
                text = stringResource(R.string.lemonade_drink_title),
                imageResource = painterResource(id = R.drawable.lemon_drink),
                imageContent = stringResource(R.string.lemonade_glass),
                onImageClick = {
                    thisStep++
                }
            )
        }
        else -> ImagesAndText(
            text = stringResource(R.string.start_again_title),
            imageResource = painterResource(id = R.drawable.lemon_restart),
            imageContent = stringResource(R.string.empty_glass)
        ) {
            thisStep = 1
        }
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
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
                .clickable(
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