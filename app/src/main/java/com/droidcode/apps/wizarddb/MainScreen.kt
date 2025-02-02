package com.droidcode.apps.wizarddb

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidcode.apps.wizarddb.data.House

@Composable
fun MainScreen(modifier: Modifier, viewModel: WizardsViewModel) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(viewModel.houseState.value) { house ->
            HousePlate(Modifier.padding(8.dp), house)
        }
    }
}

@Composable
fun HousePlate(modifier: Modifier, houseState: House) {
    var extendList by remember { mutableStateOf(false) }

    Column(
        modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.primary, MaterialTheme.shapes.small)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            )
            .padding(8.dp),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable {
                    extendList = !extendList
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                houseState.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        extendList = !extendList
                    }
            )
        }
        if (extendList) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 8.dp)
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.founder),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(houseState.founder)
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.heads),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Column {
                        houseState.heads.forEach { head ->
                            Text("${head.firstName} ${head.lastName}")
                        }
                    }
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.colours),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(houseState.houseColours)
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.animal),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(houseState.animal)
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.element),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(houseState.element)
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.ghost),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(houseState.ghost)
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.room),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(houseState.commonRoom)
                }
            }

        }
    }
}
