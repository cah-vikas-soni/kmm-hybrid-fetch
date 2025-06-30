package com.vikas.hybrid_file_download_manager

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.SYSTEM
import okio.buffer
import okio.use

class FileDownloader(private val client: HttpClient) {

    suspend fun downloadFile(url: String, fileName: String): String = withContext(Dispatchers.Default) {
        val response: HttpResponse = client.get(url)
        val bytes = response.readBytes()

        val downloadDir = FileSystemHelper.getDownloadDirectory()
        val filePath = "$downloadDir/$fileName"

        FileSystem.SYSTEM.sink(filePath.toPath()).buffer().use { sink ->
            sink.write(bytes)
        }

        return@withContext filePath
    }
}