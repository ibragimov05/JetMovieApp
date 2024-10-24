package uz.android.jetmovieapp.common.helpers

import android.content.Context
import android.graphics.drawable.PictureDrawable
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun VectorImage(
    drawableId: Int,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = contentDescription,
        modifier = modifier
    )
}