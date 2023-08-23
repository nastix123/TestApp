package by.eapp.testapp.uicomponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    //onClick: @Composable () -> Unit
) {
AssistChip(
    modifier = Modifier
        .width(98.dp)
        .height(38.dp)
        .background(color = Color(0xFFF3F5F9), shape = RoundedCornerShape(size = 100.dp))
        .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 10.dp),
onClick = {},
label = { Text("Assist Chip") },
leadingIcon = {
    Icon(
        Icons.Filled.Settings,
        contentDescription = "Localized description",
        Modifier.size(AssistChipDefaults.IconSize)
    ) }
)
}

@Preview
@Composable
fun textchip() {
    Chip()
}
@Composable
fun ChipRow() {
    LazyRow() {

    }
}