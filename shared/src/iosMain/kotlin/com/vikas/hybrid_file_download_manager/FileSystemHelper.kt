package com.vikas.hybrid_file_download_manager

import kotlinx.cinterop.BooleanVar
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import platform.Foundation.*

//actual object FileSystemHelper {
//    actual fun getDownloadDirectory(): String {
//        val directory = NSSearchPathForDirectoriesInDomains(
//            NSDocumentDirectory, NSUserDomainMask, true
//        ).first() as String
//        return "$directory/downloads"
//    }
//}

import kotlinx.cinterop.*

actual object FileSystemHelper {
    @OptIn(ExperimentalForeignApi::class)
    actual fun getDownloadDirectory(): String {
        val paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
        val documentsDir = paths.firstOrNull() as? String
            ?: error("Could not find documents directory")

        val downloadDir = "$documentsDir/downloads"
        val fileManager = NSFileManager.defaultManager

        memScoped {
            val isDir = alloc<BooleanVar>()
            if (!fileManager.fileExistsAtPath(downloadDir, isDirectory = isDir.ptr)) {
                val success = fileManager.createDirectoryAtPath(
                    path = downloadDir,
                    withIntermediateDirectories = true,
                    attributes = null,
                    error = null
                )
                if (!success) {
                    error("Failed to create download directory at $downloadDir")
                }
            }
        }

        return downloadDir
    }
}
