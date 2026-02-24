package com.audiobookmaker.main.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.audiobookmaker.R
import com.audiobookmaker.main.ui.extension.noEffectClickable
import com.audiobookmaker.main.ui.extension.rippleClickable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BookItem(
    name: String,
    date: LocalDate,
    imageUri: String?,
    pages: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    val shape = RoundedCornerShape(8.dp)
    Column(
        modifier = modifier
            .dropShadow(
                shape = shape,
                shadow = Shadow(
                    radius = 4.dp,
                    color = Color.Black.copy(alpha = 0.2f),
                    offset = DpOffset(x = 0.dp, y = 4.dp)
                )
            )
            .clip(shape = shape)
            .background(color = Color.White)
            .border(width = 2.dp, color = Color.LightGray, shape = shape)
            .rippleClickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        BookItemTop(
            name = name,
            date = date,
            onMenuClick = onMenuClick
        )
        Spacer(Modifier.height(18.dp))
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f),
            model = imageUri,
            contentDescription = "BookImage",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.icon_link_out)
        )
        Spacer(Modifier.height(10.dp))
        BookItemBottom(
            pages = pages
        )
    }
}

@Composable
private fun BookItemTop(
    name: String,
    date: LocalDate,
    onMenuClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(R.drawable.icon_book), // Want to change vector image
            contentDescription = "BookIcon"
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

            Text(
                text = date.format(formatter),
                fontSize = 14.sp
            )
        }
        Icon(
            modifier = Modifier
                .size(24.dp)
                .scale(2f)
                .clip(CircleShape)
                .rippleClickable(onClick = onMenuClick),
            imageVector = ImageVector.vectorResource(R.drawable.icon_book_menu),
            contentDescription = "BookMenuIcon"
        )
    }
}

@Composable
private fun BookItemBottom(
    pages: Int
) {
    Row(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.book_item_page, pages),
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
private fun BookItemPreview() {
    BookItem(
        name = "어린 왕자",
        date = LocalDate.now(),
        imageUri = null,
        pages = 32
    )
}