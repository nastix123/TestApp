package by.eapp.testapp.uicomponents.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainChip(
    modifier: Modifier = Modifier
) {
        ElevatedSuggestionChip(
            modifier = Modifier
                .height(38.dp)
                .background(color = Color(0xFFF3F5F9), shape = RoundedCornerShape(size = 100.dp))
                .padding(start = 20.dp, top = 10.dp, end = 20.dp, bottom = 10.dp)
                .clickable {  }
                .then(Modifier.wrapContentWidth()),
            onClick = {},
            label = {
                Text(
                    text = "Watches",
                    style = TextStyle(
                        fontSize = 7.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF1E1E1E),
                        letterSpacing = 0.28.sp,
                    )
                )
            }
        )
    }



@Preview
@Composable
fun testChip() {
    MainChip()
}
@Composable
fun ChipRow() {
    LazyRow() {
        items(7) {
            MainChip()
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun HomeScreen(){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Searchbar()
        Spacer(modifier = Modifier.height(16.dp).fillMaxWidth())
        ChipRow()
        Spacer(modifier = Modifier.height(16.dp).fillMaxWidth())
        LazyGrid()

    }
}