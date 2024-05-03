package dev.smallcat.doyourchores.ui.base

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.compose.AppTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DYCPreview(darkMode: Boolean = false, content: @Composable () -> Unit) {
    AppTheme(darkMode) {
        Scaffold (
            modifier = Modifier.fillMaxSize()
        ){
            content()
        }
    }
}