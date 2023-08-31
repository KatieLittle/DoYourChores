package dev.smallcat.doyourchores

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.smallcat.compose.DoYourChoresTheme
import dev.smallcat.doyourchores.ui.navigation.DYCScaffold

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

