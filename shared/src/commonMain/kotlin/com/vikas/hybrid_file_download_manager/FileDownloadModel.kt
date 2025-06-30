package com.vikas.hybrid_file_download_manager

data class FileDownloadModel(
    val url: String,
    val fileName: String,
    val localPath: String,
    val progress: Int = 0
)