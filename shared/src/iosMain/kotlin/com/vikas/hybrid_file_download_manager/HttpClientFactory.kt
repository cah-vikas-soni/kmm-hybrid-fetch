package com.vikas.hybrid_file_download_manager

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin

actual class HttpClientFactory {
    actual fun create(): HttpClient = HttpClient(Darwin) {
        expectSuccess = true
    }
}