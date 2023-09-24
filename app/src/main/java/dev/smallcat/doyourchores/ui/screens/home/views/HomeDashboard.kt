package dev.smallcat.doyourchores.ui.screens.home.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.smallcat.compose.DoYourChoresTheme

@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        DashboardItem("3", "Upcoming", Modifier.weight(1f))
        DashboardItem("2", "Today", Modifier.weight(1f))
        DashboardItem("0", "Overdue", Modifier.weight(1f))
    }
}

@Composable
fun DashboardItem(count: String, stateWord: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding(8.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = count, style = MaterialTheme.typography.displayLarge)
            Text(text = stateWord, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview
@Composable
private fun DefaultLightPreview() {
    DoYourChoresTheme {
        Surface {
            Dashboard()
        }
    }
}

@Preview
@Composable
private fun DefaultDarkPreview() {
    DoYourChoresTheme(true) {
        Surface {
            Dashboard()
        }
    }
}