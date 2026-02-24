package com.audiobookmaker.main.ui.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.audiobookmaker.R
import com.audiobookmaker.main.ui.component.AddFloatingButton
import com.audiobookmaker.main.ui.component.BookItem
import com.audiobookmaker.main.ui.component.SearchTextField
import com.design.topbar.TopBar
import com.design.topbar.TopBarIcon
import com.domain.model.AudioBookBoard
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import org.orbitmvi.orbit.compose.collectAsState
import java.time.LocalDate

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val uiState by viewModel.collectAsState()
    MainScreenImpl(
        modifier = modifier,
        bookList = uiState.bookList
    )
}

@Composable
fun MainScreenImpl(
    bookList: ImmutableList<AudioBookBoard>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Scaffold (
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopBar(
                title = stringResource(R.string.main_title),
                leftIcon = TopBarIcon.menuIcon(
                    onClick = { Log.e("MYLOG", "Left") }
                ),
                rightIcon = TopBarIcon.notificationIcon(
                    onClick = { Log.e("MYLOG", "Right") }
                )

            )
        },
        floatingActionButton = {
            AddFloatingButton(
                modifier = Modifier
                    .clickable(null, null) {

                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(Modifier.height(5.dp))

            SearchTextField(
                value = "",
                onValueChange = {}
            )

            Spacer(Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(
                    items = bookList,
                    key = { it.id }
                ) { book ->
                    BookItem(
                        name = book.name,
                        date = book.createdDate,
                        imageUri = book.imageUri,
                        pages = book.pages
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenImplPreview() {
    MainScreenImpl(
        bookList = persistentListOf(
            AudioBookBoard(
                name = "어린 왕자",
                createdDate = LocalDate.now(),
                imageUri = "",
                pages = 32,
                id = 1
            ),
            AudioBookBoard(
                name = "백설 공주",
                createdDate = LocalDate.now(),
                imageUri = "",
                pages = 16,
                id = 2
            )
        )
    )
}
