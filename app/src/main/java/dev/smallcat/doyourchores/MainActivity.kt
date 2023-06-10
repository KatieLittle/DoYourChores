package dev.smallcat.doyourchores

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.smallcat.compose.DoYourChoresTheme
import dev.smallcat.doyourchores.domain.FetchAllChoresUseCase
import dev.smallcat.doyourchores.domain.iFetchAllChoresUseCase
import dev.smallcat.doyourchores.repository.room.ChoreRepoRoomImpl
import dev.smallcat.doyourchores.repository.room.ChoreRoomDatabase
import dev.smallcat.doyourchores.ui.navigation.DYCScaffold
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
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

