package com.example.medistation.ui.meditationSelect


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medistation.ui.theme.BackgroundElement
import com.example.medistation.R
import com.example.medistation.ui.theme.itimFont

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
            .clickable{ onClick( ) }
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
