package com.vikas.hybrid_file_download_manager

import io.ktor.client.HttpClient

expect class HttpClientFactory {
    fun create(): HttpClient
}
