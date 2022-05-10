package com.otienosamwel.reads.ui.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpaceSmall() {
    Spacer(modifier = Modifier.size(20.dp))
}

@Composable
fun SpaceMedium() {
    Spacer(modifier = Modifier.size(40.dp))
}

@Composable
fun SpaceLarge() {
    Spacer(modifier = Modifier.size(80.dp))
}

@Composable
fun RowScope.SpaceAbsolute() {
    Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun ColumnScope.SpaceAbsolute() {
    Spacer(modifier = Modifier.weight(1f))
}

object Sizes {
    val small = 8.dp
    val medium = 16.dp
    val big = 32.dp
}