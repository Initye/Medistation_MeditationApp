package com.example.medistation.ui.meditationSelect


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medistation.ui.theme.BackgroundElement
import com.example.medistation.R
import com.example.medistation.ui.theme.itimFont
import com.example.medistation.ui.theme.musicIcon
import com.example.medistation.viewModels.ProfileViewModel

@Composable
fun Element(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    title: String,
    image: Int,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BackgroundElement)
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 36.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 40.dp),
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(modifier.weight(1f))
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = image),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun ProfileElement(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BackgroundElement)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(
                text = title,
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp, top = 30.dp, bottom = 30.dp),
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
            Spacer(modifier.weight(1f))
            Text(
                text = description,
                fontSize = 28.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 40.dp, end = 20.dp),
                fontFamily = itimFont, fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun MusicElement(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel
) {
    // Preset for border
    fun Modifier.circularBorder(
        width: Dp = 0.4.dp,
        color: Color = Color.White
    ) = this
        .clip(CircleShape)
        .border(width = width, color = color, shape = CircleShape)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(BackgroundElement),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                Image(
                    modifier = modifier
                        .size(musicIcon)
                        .circularBorder()
                        .clickable(onClick = { profileViewModel.getMusicType("null")}),
                    painter = painterResource(R.drawable.x_icon),
                    contentDescription = "No music",
                )
                Image(
                    modifier = modifier
                        .size(musicIcon)
                        .circularBorder()
                        .clickable(onClick = { profileViewModel.getMusicType("meditation")}),
                    painter = painterResource(R.drawable.meditation_icon),
                    contentDescription = "Meditation music",
                )
                Image(
                    modifier = modifier
                        .size(musicIcon)
                        .circularBorder()
                        .clickable(onClick = { profileViewModel.getMusicType("rain")}),
                    painter = painterResource(R.drawable.rain_icon),
                    contentDescription = "Rain music",
                    )
                Image(
                    modifier = modifier
                        .size(musicIcon)
                        .circularBorder()
                        .clickable(onClick = { profileViewModel.getMusicType("lofi")}),

                    painter = painterResource(R.drawable.lofi_icon),
                    contentDescription = "Lofi music",
                    )
        }
    }
}



//Make a dataclass for elements displaying
data class MeditationItem (
    val title: String,
    val image: Int,
    val destination: String
)
//Set values for dataclass
val meditations = listOf(
    MeditationItem("Relax", R.drawable.meditate, "relaxMed"),
    MeditationItem("Calm", R.drawable.meditate_calm, "calmMed"),
    MeditationItem("Rain", R.drawable.meditate_rain, "rainMed"),
    MeditationItem("Focus", R.drawable.meditate_focus, "focusMed"),
    MeditationItem("Sleep", R.drawable.meditate_sleep, "sleepMed"),
)

@Preview
@Composable
fun ElementPreview() {
    Element(onClick = {}, title = "Design", image = R.drawable.meditate)
}

@Preview
@Composable
fun ElementProfilePreview() {
    ProfileElement(title="Total time", description = "10h 20m 42s")
}

@Preview
@Composable
fun MusicElementPreview() {
    MusicElement(profileViewModel = viewModel())
}

