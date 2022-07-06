package com.otienosamwel.reads.ui.presentation.features.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Discover(discoverViewModel: DiscoverViewModel = hiltViewModel()) {

    val discoverNavController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarScrollState())

    Scaffold(
        topBar = { DiscoverTopAppBar(scrollBehavior) },
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { scaffoldPaddingValue ->

        NavHost(
            navController = discoverNavController,
            modifier = Modifier.padding(scaffoldPaddingValue),
            startDestination = DiscoverScreens.DiscoverMain.route
        ) {
            composable(DiscoverScreens.DiscoverMain.route) {
                DiscoverMain(
                    navController = discoverNavController,
                    discoverViewModel = discoverViewModel
                )
            }
            composable(DiscoverScreens.Search.route) { Search(discoverNavController = discoverNavController) }
        }

    }
}

@Composable
fun DiscoverMain(navController: NavHostController, discoverViewModel: DiscoverViewModel) {
    Column(modifier = Modifier) {
        SearchBar(navController, discoverViewModel = discoverViewModel)

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            //main discover content will go here
        }

    }

}

@Composable
fun DiscoverTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    SmallTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "Discover") },
        scrollBehavior = scrollBehavior
    )

}

@Composable
fun SearchBar(discoverNAvController: NavHostController, discoverViewModel: DiscoverViewModel) {

    OutlinedTextField(
        value = "",
        readOnly = true,
        enabled = false,
        onValueChange = {},
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
            .clickable {
                discoverNAvController.navigate(DiscoverScreens.Search.route)
            },
        placeholder = {
            Text(text = "Books and authors")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
        }
    )
}

sealed class DiscoverScreens(val route: String) {
    object DiscoverMain : DiscoverScreens("discoverMain")
    object Search : DiscoverScreens("search")
}