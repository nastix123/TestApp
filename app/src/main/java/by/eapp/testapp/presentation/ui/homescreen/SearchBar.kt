
package by.eapp.testapp.presentation.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.eapp.testapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searchbar() {
    var message by remember {
        mutableStateOf("")
    }
    TextField(
        value = message,
        onValueChange = { message = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .border(width = 1.4.dp, color = Color(0xFFE29FA5))
                    .padding(1.4.dp)
                    .width(16.dp)
                    .height(16.dp)
            )
        },
        placeholder = {
            Text(stringResource(R.string.place_holder))
        },
        modifier = Modifier
            .padding(0.5.dp)
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = Color(0xFFF3F5F9),
                shape = RoundedCornerShape(50.dp)
            )
    )
}



@Preview(showBackground = true)
@Composable
fun testSearchBar() {
    Searchbar()
}