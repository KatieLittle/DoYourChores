package dev.smallcat.doyourchores.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.smallcat.compose.DoYourChoresTheme
import dev.smallcat.doyourchores.domain.models.Chore
import dev.smallcat.doyourchores.commonmodels.enums.DueState
import dev.smallcat.doyourchores.ui.di.viewModelFactory
import dev.smallcat.myapplication.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeVM: HomeViewModel = viewModel (
        factory = viewModelFactory { HomeViewModel() }
    )
) {

    val state by remember{homeVM.state}
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Dashboard()
            Divider(modifier = Modifier.padding(8.dp))
            ChoreList(list = state.chores)
        }
    }
}

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


@Composable
fun ChoreList(modifier: Modifier = Modifier, list: List<Chore>) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        list.forEach{
            ChoreCard(it.name, "Yesterday", DueState.OVERDUE)

        }
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

    val textColour = contentColorFor(backgroundColour)

    Card(
        modifier = modifier
            .padding(8.dp)
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

@Preview
@Composable
fun DefaultLightPreview() {
    DoYourChoresTheme {
        Surface() {
            HomeScreen()
        }
    }
}

@Preview
@Composable
fun DefaultDarkPreview() {
    DoYourChoresTheme(true) {
        Surface() {
            HomeScreen()
        }
    }
}