package com.vikas.hybrid_file_download_manager.android

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File

@Composable
fun FileDownloadScreen(
    onDownloadClicked: (url: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) -> Unit
) {
    val context = LocalContext.current
    var url by remember { mutableStateOf("") }
    var isDownloading by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf("") }
    var imagePath by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = url,
            onValueChange = { url = it },
            label = { Text("Enter File URL") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Uri
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                isDownloading = true
                resultText = ""
                onDownloadClicked(
                    url,
                    {
                        isDownloading = false
                        resultText = "Downloaded to: $it"
                        imagePath = it
                    }, {
                        isDownloading = false
                        resultText = "Error: $it"
                        imagePath = null
                    }
                )
            },
            enabled = !isDownloading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Download")
        }

        if (isDownloading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        if (resultText.isNotEmpty()) {
            Text(resultText)
        }

        Spacer(modifier = Modifier.height(16.dp))

        imagePath?.let { path ->
            val bitmap = remember(path) {
                val file = File(path)
                if (file.exists()) {
                    BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap()
                } else null
            }

            bitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = "Downloaded image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }
        }
    }
}