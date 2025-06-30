package com.vikas.hybrid_file_download_manager.android

import android.app.Application
import com.vikas.hybrid_file_download_manager.initFileSystemHelper

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initFileSystemHelper(this)
    }
}
