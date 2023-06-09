package com.example.doyourchores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose.DoYourChoresTheme
import com.example.doyourchores.ui.navigation.DYCScaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoYourChoresTheme {
                DYCScaffold()
            }
        }
    }
}

