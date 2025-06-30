@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.vikas.hybrid_file_download_manager

expect object FileSystemHelper {
    fun getDownloadDirectory(): String
}