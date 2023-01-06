package com.otienosamwel.reads.ui.presentation.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.otienosamwel.reads.R
import com.otienosamwel.reads.ui.presentation.components.SpaceSmall
import com.otienosamwel.reads.ui.theme.ReadsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController?) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { HomeTopAppBar(scrollBehavior) }) { scaffoldPaddingValues ->
        Column(
            modifier = Modifier
                .padding(scaffoldPaddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpaceSmall()

            AuthorOfTheWeek()

            BookOfTheWeek()

            SpaceSmall()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = { Text(text = "Reads") },
        modifier = Modifier.fillMaxWidth(),
        scrollBehavior = scrollBehavior
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomePreview() {
    ReadsTheme {
        Surface(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            Home(navController = null)
        }
    }
}

@Composable
fun AuthorOfTheWeek() {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Author of the week",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            Text(text = "Maggie O'Farrell", style = MaterialTheme.typography.displaySmall)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Card {
                    AsyncImage(
                        model = R.drawable.placeholder_author,
                        contentDescription = null,
                        modifier = Modifier
                            .width(150.dp)
                            .wrapContentHeight(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(
                text = placeholderBio,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
fun BookOfTheWeek() {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Book of the week",
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(bottom = 8.dp)
            )

            Text(text = "House of Chains", style = MaterialTheme.typography.displaySmall)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Card {
                    AsyncImage(
                        model = R.drawable.book_cover_placeholder,
                        contentDescription = null,
                        modifier = Modifier
                            .width(150.dp)
                            .wrapContentHeight(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Text(
                text = placeholderBio,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

private val placeholderBio =
    "Danielle Geller is a writer of personal essays and memoir. She received her MFA in creative writing for nonfiction at the University of Arizona, and a Rona Jaffe Writers' Award in 2016. Her work has appeared in The New Yorker, Brevity, and Arizona Highways, and has been anthologized in This Is the Place. She lives with her husband and two cats in British Columbia, where she teaches creative writing at the University of Victoria. She is a member of the Navajo Nation: born to the Tsi'naajinii, born for the white man."