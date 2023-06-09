package com.example.doyourchores.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.DoYourChoresTheme
import com.example.doyourchores.enums.DueState
import com.example.myapplication.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeVM: HomeViewModel = viewModel()) {

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Dashboard()
            ChoreList()
        }
    }
}

@Composable
fun Dashboard(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        DashboardItem("3", "Upcoming", Modifier.weight(1f))
        DashboardItem("2", "Today", Modifier.weight(1f))
        DashboardItem("0", "Overdue", Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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


@Composable
fun ChoreList(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        ChoreCard("Water Plants", "Yesterday", DueState.OVERDUE)
        ChoreCard("Hoover Kitchen", "Today", DueState.TODAY)
        ChoreCard("Laundry", "Today", DueState.TODAY)
        ChoreCard("Clean Main Bathroom", "in 2 days", DueState.UPCOMING)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoreCard(choreName: String, due: String, dueState: DueState, modifier: Modifier = Modifier) {

    val backgroundColour = when (dueState) {
        DueState.UPCOMING -> MaterialTheme.colorScheme.surfaceVariant
        DueState.TODAY -> MaterialTheme.colorScheme.primaryContainer
        DueState.OVERDUE -> MaterialTheme.colorScheme.tertiaryContainer
    }

    val textColour = MaterialTheme.colorScheme.onPrimaryContainer //contentColorFor(backgroundColour)

    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColour
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                Text(
                    text = choreName,
                    style = MaterialTheme.typography.headlineSmall,
                    color = textColour
                )
                Text(
                    text = "Due $due",
                    style = MaterialTheme.typography.bodyMedium,
                    color = textColour
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "checked"
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultLightPreview() {
    DoYourChoresTheme {
        Surface() {
            HomeScreen()
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultDarkPreview() {

    DoYourChoresTheme {
        Surface() {
            HomeScreen()
        }
    }
}