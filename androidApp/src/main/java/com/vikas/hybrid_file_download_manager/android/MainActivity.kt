package com.vikas.hybrid_file_download_manager.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.vikas.hybrid_file_download_manager.FileDownloader
import com.vikas.hybrid_file_download_manager.HttpClientFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val downloader = FileDownloader(HttpClientFactory().create())
        setContent {
            MyApplicationTheme {
                FileDownloadScreen { url, onSuccess, onError ->
                    val fileName = url.substringAfterLast("/")
                    lifecycleScope.launch {
                        try {
                            val path = withContext(Dispatchers.IO) {
                                downloader.downloadFile(url, fileName)
                            }
                            onSuccess(path)
                        } catch (e: Exception) {
                            onError(e.message ?: "Unknown error")
                        }
                    }
                }
            }
        }
    }
}