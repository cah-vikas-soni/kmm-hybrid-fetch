package com.vikas.hybrid_file_download_manager

import android.content.Context
import java.io.File

lateinit var appContext: Context

fun initFileSystemHelper(context: Context) {
    appContext = context.applicationContext
}

actual object FileSystemHelper {
    actual fun getDownloadDirectory(): String {
        val dir = File(appContext.filesDir, "downloads")
        dir.mkdirs()
        return dir.absolutePath
    }
}
