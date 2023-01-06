package com.otienosamwel.reads.ui.presentation.features.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.otienosamwel.reads.ui.presentation.components.BookItem
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Discover(discoverViewModel: DiscoverViewModel = hiltViewModel()) {

    val discoverNavController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val navBackStackEntry by discoverNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        topBar = {
            if (currentDestination?.hierarchy?.any { it.route == DiscoverScreens.DiscoverMain.route } == true)
                DiscoverTopAppBar(scrollBehavior)
        },
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
    Column(modifier = Modifier.fillMaxSize()) {
        SpaceMedium()
        SearchBar(navController)
        SpaceMedium()

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(genres) { genre ->
                DiscoverGenreHorizontalList(genre = genre, discoverViewModel = discoverViewModel)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    SmallTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "Discover") },
        scrollBehavior = scrollBehavior
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(discoverNAvController: NavHostController) {

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
        }, shape = RoundedCornerShape(120.dp)
    )
}

@Composable
fun DiscoverGenreHorizontalList(genre: String, discoverViewModel: DiscoverViewModel) {

    val booksState = remember { discoverViewModel.getGenreList(genre) }
    val books by booksState.collectAsState(listOf())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {

        Text(text = genre, modifier = Modifier.padding(horizontal = 12.dp))

        SpaceSmall()

        LazyRow(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(books) { book ->
                book.volumeInfo?.imageLinks?.thumbnail?.let { coverLink ->
                    BookItem(
                        book = book,
                        coverLink = coverLink,
                        onCoverClick = {},
                        onAuthorClick = {})
                }
            }
        }
    }

}

sealed class DiscoverScreens(val route: String) {
    object DiscoverMain : DiscoverScreens("discoverMain")
    object Search : DiscoverScreens("search")
}

private val genres =
    listOf(
        "Novel",
        "Children's Literature",
        "Self Help",
        "Magical Realism",
        "Art",
        "Fiction",
        "Paranormal Romance",
        "Novella",
        "Adult Fiction",
        "True Crime",
        "Motivation",
        "Comedy",
        "Travel Literature",
        "Young Adult Fantasy",
        "Fantasy",
        "Drama",
        "History",
        "Science",
        "Biography",
        "Romance",
        "Horror",
        "Mystery",
        "Thriller",
        "Science Fiction",
        "Historical Fiction",
        "Fantasy Fiction",
        "Adventure",
        "Poetry",
        "Fan Fiction",
        "Lifestyle"
    )

private const val TAG = "DISCOVER"