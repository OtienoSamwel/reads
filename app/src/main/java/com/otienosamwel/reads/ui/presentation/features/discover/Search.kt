package com.otienosamwel.reads.ui.presentation.features.discover

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.otienosamwel.reads.data.model.Item
import com.otienosamwel.reads.data.model.SearchItem
import com.otienosamwel.reads.ui.presentation.components.ImageView
import com.otienosamwel.reads.ui.presentation.components.Loading
import com.otienosamwel.reads.ui.presentation.components.SpaceAbsolute
import com.otienosamwel.reads.ui.presentation.components.SpaceMedium

@Composable
fun Search(
    discoverNavController: NavController, discoverViewModel: DiscoverViewModel = hiltViewModel()
) {
    val searchResultItems = discoverViewModel.searchResponseItems.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            MainSearchBar(discoverViewModel, discoverNavController)
            SearchHistoryDropDown(discoverViewModel)
        }


        if (discoverViewModel.discoverState.isSearchLoading) Loading()

        searchResultItems.value?.items?.let { bookItems ->

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(bookItems) { bookItem ->
                    bookItem.volumeInfo?.imageLinks?.thumbnail?.let {

                        SearchItem(book = bookItem,
                            coverLink = it,
                            onCoverClick = { },
                            onAuthorClick = {})

                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainSearchBar(discoverViewModel: DiscoverViewModel, discoverNavController: NavController) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    OutlinedTextField(
        value = discoverViewModel.discoverState.searchQuery,
        singleLine = true,
        maxLines = 1,
        onValueChange = { newValue ->
            discoverViewModel.discoverState.onSearchQueryChange(newValue)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            discoverViewModel.search(context as ComponentActivity)
            keyboardController?.hide()
            focusManager.clearFocus()
        }),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        leadingIcon = {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.clickable {
                    discoverNavController.popBackStack()
                })
        },
        trailingIcon = {
            if (discoverViewModel.discoverState.searchQuery.isNotBlank() &&
                discoverViewModel.discoverState.searchQuery.isNotEmpty()
            ) Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = null,
                modifier = Modifier.clickable {
                    discoverViewModel.discoverState.onSearchQueryChange("")
                })
        },
        placeholder = {
            Text(text = "Search query")
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        shape = RectangleShape
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun SearchHistoryDropDown(discoverViewModel: DiscoverViewModel) {
    val context = LocalContext.current as ComponentActivity
    val focusManager = LocalFocusManager.current
    DropdownMenu(
        expanded = discoverViewModel.discoverState.isHistoryDropDownVisible,
        onDismissRequest = { },
        modifier = Modifier.fillMaxWidth(),
        properties = PopupProperties(focusable = false)
    ) {
        discoverViewModel.searchHistoryFlow.collectAsState(initial = listOf()).value.forEach { searchItem ->
            DropdownMenuItem(text = {
                SearchDropDownItem(
                    searchItem = searchItem, discoverViewModel
                )
            }, onClick = {
                discoverViewModel.discoverState.onSearchQueryChange(searchItem.searchQuery)
                discoverViewModel.search(context)
                focusManager.clearFocus()
            })
        }
    }
}

@Composable
fun SearchDropDownItem(searchItem: SearchItem, discoverViewModel: DiscoverViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Rounded.Refresh, contentDescription = null)

        SpaceMedium()

        Text(text = searchItem.searchQuery, modifier = Modifier.weight(1f))

        IconButton(onClick = { discoverViewModel.deleteSearchItem(searchItem) }) {
            Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
        }
    }
}


@Composable
fun SearchItem(
    book: Item,
    coverLink: String,
    onCoverClick: () -> Unit,
    onAuthorClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                horizontal = 10.dp,
                vertical = 4.dp
            )
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,

        ) {

        ImageView(
            size = 90,
            imageLink = coverLink,
            onClick = onCoverClick
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(144.dp),
        ) {
            SpaceAbsolute()

            Text(
                text = book.volumeInfo?.title ?: "Title unavailable",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = book.volumeInfo?.authors?.first() ?: "Author unavailable",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Black,
                modifier = Modifier.clickable { onAuthorClick() }
            )

            Text(
                text = book.volumeInfo?.description ?: "",
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}