package com.audiobookmaker.ui.main

import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview as CameraXPreview
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.audiobookmaker.R
import com.audiobookmaker.util.imageProxyToJpegByteArray
import java.util.concurrent.Executors

@Composable
fun MainActivityScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    MainActivityScreenImpl(
        modifier = modifier,
        uploadImage = viewModel::uploadImageFile
    )
}

@Composable
fun MainActivityScreenImpl(
    modifier: Modifier = Modifier,
    uploadImage: (ByteArray) -> Unit
) {
    val context = LocalContext.current
    val imageCapture = remember {
        ImageCapture.Builder()
            .setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY)
            // 플래시 모드 설정: 자동 (기본값)
            .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
            .build()
    }
    val executorService = remember { ContextCompat.getMainExecutor(context) }
    val imageSaverCallback = remember {
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(imageProxy: ImageProxy) {
                val imageBytes: ByteArray? = imageProxyToJpegByteArray(imageProxy)
                Log.e("MYLOG", "${imageBytes}")
                imageBytes?.let{
                    uploadImage(imageBytes)
                }
                imageProxy.close()
            }
            override fun onError(exc: ImageCaptureException) {
                Log.e("MYLOG", "Photo capture failed: ${exc.message}", exc)
            }
        }
    }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val cameraSelector = remember { CameraSelector.DEFAULT_BACK_CAMERA }
    val lifecycleOwner = LocalLifecycleOwner.current
    val preview = remember { CameraXPreview.Builder().build() }
    LaunchedEffect(Unit) {
        val cameraProvider = cameraProviderFuture.get()

        cameraProviderFuture.addListener({
            try {
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )

            } catch (exc: Exception) {
                Log.e("MYLOG", "Use case binding failed", exc)
            }
        }, executorService)
    }
    DisposableEffect(Unit) {
        onDispose {
            val cameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.Blue.copy(alpha = 0.3f),
                    shape = ShapeDefaults.Small
                )
                .clip(shape = ShapeDefaults.Small)
                .clickable {
                    imageCapture.takePicture(
                        executorService,
                        imageSaverCallback
                    )
                }
                .padding(vertical = 12.dp, horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            BasicText(
                text = stringResource(R.string.capture_image_button),
                style = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Spacer(Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .background(
                    color = Color.Green.copy(alpha = 0.3f),
                    shape = ShapeDefaults.Small
                )
                .clip(shape = ShapeDefaults.Small)
                .clickable {

                }
                .padding(vertical = 12.dp, horizontal = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            BasicText(
                text = stringResource(R.string.send_button),
                style = TextStyle.Default.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun Builder() {
    TODO("Not yet implemented")
}

@Preview(showBackground = true)
@Composable
fun MainActivityScreenImplPreview() {
    MainActivityScreenImpl(
        uploadImage = {}
    )
}