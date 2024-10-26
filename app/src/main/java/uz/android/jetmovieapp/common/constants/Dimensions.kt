package uz.android.jetmovieapp.common.constants

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SpacerH24() {
    Spacer(
        modifier = Modifier.height(
            24.dp
        )
    )
}

@Composable
fun SpacerH16() {
    Spacer(
        modifier = Modifier.height(
            16.dp
        )
    )
}

@Composable
fun SpacerH12() {
    Spacer(
        modifier = Modifier.height(
            12.dp
        )
    )
}

val paddingH16: Modifier = Modifier.padding(horizontal = 16.dp)