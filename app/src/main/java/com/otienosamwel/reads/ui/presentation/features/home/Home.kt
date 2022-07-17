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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.otienosamwel.reads.ui.presentation.components.ImageView
import com.otienosamwel.reads.ui.theme.ReadsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController?) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarScrollState())

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
        }

    }
}

@Composable
fun HomeTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    SmallTopAppBar(
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

//@Preview
//@Composable
//fun Section() {
//    Column(
//        modifier = Modifier
//            .padding(10.dp)
//            .fillMaxWidth(), horizontalAlignment = Alignment.Start
//    ) {
//        Text(
//            text = "Section Title",
//            modifier = Modifier.padding(start = 5.dp),
//            style = MaterialTheme.typography.labelLarge
//        )
//
//        SpaceSmall()
//
//        LazyRow(
//            contentPadding = PaddingValues(horizontal = 5.dp),
//            horizontalArrangement = Arrangement.spacedBy(20.dp)
//        ) {
//            items(20) {
//                BookItem(
//                    book = Book(
//                        name = "The tombs of Atuan",
//                        author = "Patrick Rothfuss"
//                    ), onAuthorClick = {}, onCoverClick = {}
//                )
//            }
//        }
//    }
//}