package com.vikas.hybrid_file_download_manager

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual class HttpClientFactory {
    actual fun create(): HttpClient = HttpClient(OkHttp) {
        expectSuccess = true
    }
}