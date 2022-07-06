package com.otienosamwel.reads.ui.presentation.features.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.otienosamwel.reads.ui.presentation.features.account.Account
import com.otienosamwel.reads.ui.presentation.features.collections.Collections
import com.otienosamwel.reads.ui.presentation.features.discover.Discover
import com.otienosamwel.reads.ui.presentation.features.home.Home
import com.otienosamwel.reads.ui.theme.ReadsTheme
import com.otienosamwel.reads.utils.ClearRippleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReadsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { Navigation() }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation() {
    val mainNavController = rememberNavController()
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNav(mainNavController) })
    { scaffoldPaddingValues ->
        NavHost(
            mainNavController,
            startDestination = Screens.Discover.route,
            modifier = Modifier.padding(scaffoldPaddingValues)
        ) {
            composable(route = Screens.Home.route) { Home(navController = mainNavController) }
            composable(route = Screens.Discover.route) { Discover() }
            composable(route = Screens.Account.route) { Account() }
            composable(route = Screens.Collections.route) { Collections() }
        }
    }
}

@Composable
fun BottomNav(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    CompositionLocalProvider(LocalRippleTheme provides ClearRippleTheme) {
        NavigationBar(modifier = Modifier.fillMaxWidth()) {
            bottomNavScreens.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    icon = { Icon(imageVector = screen.Icon!!, contentDescription = screen.name) },
                    label = { Text(text = screen.name) },
                    alwaysShowLabel = true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

private sealed class Screens(val route: String, val name: String, val Icon: ImageVector?) {
    object Home : Screens("home", "Home", Icons.Rounded.Home)
    object Discover : Screens("discover", "Discover", Icons.Rounded.Search)
    object Account : Screens("account", "Account", Icons.Rounded.AccountCircle)
    object Collections : Screens("collections", "Collections", Icons.Rounded.Add)
}

private val bottomNavScreens =
    listOf(Screens.Home, Screens.Discover, Screens.Collections, Screens.Account)