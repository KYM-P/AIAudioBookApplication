package com.design.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.design.extension.noEffectClickable
import com.design.R

@Composable
fun DropDownBox(
    items: List<String>,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontSize = 14.sp),
    onItemSelected: (String) -> Unit = {}
) {
    val density = LocalDensity.current
    var selectedItem by remember { mutableStateOf(items.firstOrNull() ?: "") }
    var expanded by remember { mutableStateOf(false) }
    var rowHeight by remember { mutableStateOf(0) }
    var rowWidthDp by remember { mutableStateOf(0.dp) }

    Box (
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = modifier
                .background(Color.White)
                .noEffectClickable {
                    expanded = !expanded
                }
                .onGloballyPositioned { coordinates ->
                    rowWidthDp = with(density) { coordinates.size.width.toDp() }
                    rowHeight = coordinates.size.height + 10
                }
                .padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = selectedItem,
                    style = textStyle
                )
                /*
                 * To show constant layout size
                 */
                key(items) {
                    val longestTextItem = items.maxByOrNull { it.length }
                    Text(
                        modifier = Modifier.padding(start = 4.dp).alpha(0f),
                        text = longestTextItem ?: ""
                    )
                }
            }
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_drop_down),
                contentDescription = "dropdownIcon"
            )
        }

        if(expanded) {
            Popup(
                offset = IntOffset(0, rowHeight),
                onDismissRequest = { expanded = false }
            ) {
                Column(
                    modifier = Modifier
                        .width(rowWidthDp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(
                            width =1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items.forEach { item ->
                        Text(
                            text = item,
                            modifier = Modifier.Companion
                                .noEffectClickable {
                                    selectedItem = item
                                    onItemSelected(item)
                                    expanded = false
                                }
                                .padding(12.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DropDownBoxPreview() {
    DropDownBox(
        items = listOf("최신 순", "오래된 순")
    )
}