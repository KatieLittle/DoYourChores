package dev.smallcat.doyourchores.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.smallcat.compose.DoYourChoresTheme
import dev.smallcat.doyourchores.ui.common.models.DueState
import dev.smallcat.doyourchores.domain.models.Chore
import dev.smallcat.myapplication.R

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

@Composable
private fun ChoreCard(choreName: String, due: String, dueState: DueState, modifier: Modifier = Modifier) {

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
private fun DefaultLightPreview() {
    DoYourChoresTheme {
        Surface {
            ChoreList(list = listOf())
        }
    }
}

@Preview
@Composable
private fun DefaultDarkPreview() {
    DoYourChoresTheme(true) {
        Surface {
            ChoreList(list = listOf())
        }
    }
}