package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtScreen()
                }
            }
        }
    }
}

@Composable
fun ArtScreen() {
    var screen: Screen by remember { mutableStateOf(Screen.HorseWoman) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .scrollable(orientation = Orientation.Horizontal, state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Surface(
            tonalElevation = 12.dp,
            color = Color.White,
            shadowElevation = 12.dp
        ) {
            Image(
                painterResource(screen.currentArtWork.imageId),
                contentDescription = "test",
                modifier = Modifier.padding(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            stringResource(screen.currentArtWork.titleId),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        TitleAndAuthorInformation(
            authorLabel = screen.currentArtWork.authorId,
            yearLabel = screen.currentArtWork.yearId
        )
    }
    PrevAndNextButtons(
        onClickPrev = { screen = getPrevScreen(screen) },
        onClickNext = { screen = getNextScreen(screen) }
    )
}


fun getPrevScreen(currentScreen: Screen): Screen{
    if(currentScreen is Screen.HorseWoman)
        return Screen.Tarakanova

    if(currentScreen is Screen.Tarakanova)
        return Screen.Alenushka

    return Screen.HorseWoman
}

fun getNextScreen(currentScreen: Screen): Screen{
    if(currentScreen is Screen.HorseWoman)
        return Screen.Alenushka

    if(currentScreen is Screen.Alenushka)
        return Screen.Tarakanova

    return Screen.HorseWoman
}

@Composable
fun TitleAndAuthorInformation(
    @StringRes authorLabel: Int,
    @StringRes yearLabel: Int
){
    Row(horizontalArrangement = Arrangement.Center) {
        Text(
            stringResource(authorLabel),
            fontSize = 16.sp
        )
        Text(
            buildAnnotatedString {
                append(" ")
                append("(")
                append(stringResource(yearLabel))
                append(")")
            },
            fontSize = 16.sp
        )
    }
}

@Composable
fun PrevAndNextButtons(
    onClickPrev: () -> Unit,
    onClickNext:() -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClickPrev,
            modifier = Modifier.weight(0.5f)
        ) {
            Text("Previous")
        }
        Spacer(modifier = Modifier.width(20.dp))
        Button(
            onClick = onClickNext,
            modifier = Modifier.weight(0.5f)
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        ArtScreen()
    }
}