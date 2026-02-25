package com.audiobookmaker.main.ui.addbook

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.audiobookmaker.R
import com.design.topbar.TopBar
import com.design.topbar.TopBarIcon

@Composable
fun AddBookScreen(
    modifier: Modifier = Modifier,
    viewModel: AddBookViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    AddBookScreenImpl(
        modifier = modifier
    )
}

@Composable
fun AddBookScreenImpl(
    modifier: Modifier = Modifier
) {
    Scaffold (
        modifier = Modifier
            .systemBarsPadding(),
        topBar = {
            TopBar(
                title = stringResource(R.string.main_title),
                leftIcon = TopBarIcon.menuIcon(
                    onClick = { Log.e("MYLOG", "Left") }
                )
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

        }
    }
}

@Preview
@Composable
fun AddBookImplPreview() {
    AddBookScreenImpl()
}