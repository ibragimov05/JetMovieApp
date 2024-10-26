package uz.android.jetmovieapp.feature.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uz.android.jetmovieapp.common.constants.SpacerH24
import uz.android.jetmovieapp.common.theme.AppColors
import uz.android.jetmovieapp.common.widgets.SearchField
import uz.android.jetmovieapp.feature.main.MainViewModel
import uz.android.jetmovieapp.feature.search.SearchScreen

@Composable
fun HomeScreen(navController: NavController) {
    val statusBarHeight = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val mainVM = hiltViewModel<MainViewModel>()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = AppColors.mainColor,
    ) {
        Column(
            modifier = Modifier.padding(
                PaddingValues(start = 16.dp, end = 16.dp, top = statusBarHeight, bottom = 0.dp)
            )
        ) {
            Text(
                "What do you want to watch?",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W600,
                    color = AppColors.white,
                ),
            )
            SpacerH24()

            SearchField(
                enabled = false,
                onTap = {
                    mainVM.updateIndex(1)
                }
            )

            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) { }
        }
    }
}


