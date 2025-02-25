package com.droidcode.apps.wizarddb.views

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droidcode.apps.wizarddb.R
import com.droidcode.apps.wizarddb.WizardsViewModel
import com.droidcode.apps.wizarddb.data.Elixir
import com.droidcode.apps.wizarddb.data.House
import com.droidcode.apps.wizarddb.data.Spell

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(modifier: Modifier, viewModel: WizardsViewModel) {
    val tabItems = listOf(
        TabItem(stringResource(R.string.houses)),
        TabItem(stringResource(R.string.spells)),
        TabItem(stringResource(R.string.elixirs))
    )

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Column(modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = {
                        Text(item.title)
                    }
                )
            }
        }
        HorizontalPager(
            pagerState,
            Modifier
                .fillMaxSize()
                .weight(1f)
        ) { index ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                if (selectedTabIndex == 0) {
                    items(viewModel.houseState.value) { house ->
                        HousePlate(Modifier.padding(8.dp), house)
                    }
                } else if (selectedTabIndex == 1) {
                    items(viewModel.spellState.value) { spell ->
                        SpellPlate(Modifier.padding(8.dp), spell)
                    }
                } else {
                    items(viewModel.elixirState.value) { elixir ->
                        ElixirPlate(Modifier.padding(8.dp), elixir)
                    }
                }
            }
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

@Composable
fun SpellPlate(modifier: Modifier, spellState: Spell) {
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
                spellState.name,
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
                if (spellState.incantation?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.incantation),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(spellState.incantation)
                    }
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.spell_effect),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(spellState.effect)
                }

                Row(Modifier.fillMaxWidth()) {
                    Text(
                        stringResource(R.string.can_be_verbal),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.padding(horizontal = 2.dp))
                    Text(spellState.canBeVerbal.toString())
                }

                if (spellState.type?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.type),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(spellState.type)
                    }
                }

                if (spellState.light?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.light),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(spellState.light)
                    }
                }

                if (spellState.creator?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.creator),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(spellState.creator)
                    }
                }
            }
        }
    }
}

@Composable
fun ElixirPlate(modifier: Modifier, elixirState: Elixir) {
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
                elixirState.name,
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
                if (elixirState.effect?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.effect),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(elixirState.effect)
                    }
                }

                if (elixirState.sideEffects?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.side_effects),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(elixirState.sideEffects)
                    }
                }

                if (elixirState.characteristics?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.characteristics),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(elixirState.characteristics)
                    }
                }

                if (elixirState.time?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.time),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(elixirState.time)
                    }
                }

                if (elixirState.difficulty?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.difficulty),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(elixirState.difficulty)
                    }
                }

                if (elixirState.ingredients.isNotEmpty()) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.ingredients),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Column {
                            elixirState.ingredients.forEach { ingredient ->
                                Text(ingredient.name)
                            }
                        }
                    }
                }

                if (elixirState.inventors.isNotEmpty()) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.inventors),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Column {
                            elixirState.inventors.forEach { inventor ->
                                Text("${inventor.firstName} ${inventor.lastName}")
                            }
                        }
                    }
                }

                if (elixirState.manufacturer?.isNotEmpty() == true) {
                    Row(Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(R.string.manufacturer),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(Modifier.padding(horizontal = 2.dp))
                        Text(elixirState.manufacturer)
                    }
                }
            }
        }
    }
}
