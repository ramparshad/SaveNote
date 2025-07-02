package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todo.RoomDatabaseUse.AppUi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainSplashScreen()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Revprev(){
    AppUi()
}