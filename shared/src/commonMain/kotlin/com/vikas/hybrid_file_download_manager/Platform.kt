package com.vikas.hybrid_file_download_manager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform