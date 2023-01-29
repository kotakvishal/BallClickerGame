package com.example.ballclickergame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

/***
 * Composable for Main Screen
 */
@Composable
fun MainScreen(){
    var points by remember {
        mutableStateOf(0)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
           Text(text = "Points: $points",
           fontSize = 20.sp,
           fontWeight = FontWeight.Bold
           )
            Button(onClick = {
                isTimerRunning=!isTimerRunning
                points =0
            }) {
                Text(text = if (isTimerRunning) "Reset" else "Start")
                
            }
            CountDownTimer(isTimerRunning =isTimerRunning){
                isTimerRunning =false
            }
        }
    }
}

/**
 * Composable for Count Down Timer
 */
@Composable
fun CountDownTimer(
    time: Int=30000,
    isTimerRunning :Boolean =false,
    onTimerEnd : ()-> Unit= {}
){
    var curTim by remember {
        mutableStateOf(time)
    }
    LaunchedEffect(key1 = curTim, key2 = isTimerRunning ){
        if(!isTimerRunning){
            curTim=time
            return@LaunchedEffect
        }
        if(curTim>0){
            delay(1000L)
            curTim-=1000
        }
        else{
            onTimerEnd()
        }
    }
    Text(
        text =(curTim/1000).toString(),
    fontSize = 20.sp,
    fontWeight = FontWeight.Bold)
}

