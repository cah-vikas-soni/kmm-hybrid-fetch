📦 KMM File Downloader Plugin
A Kotlin Multiplatform Mobile (KMM) based file downloader library designed to simplify and unify file downloading across Android and iOS platforms.

🎯 Features
✅ Cross-platform core logic (Kotlin Multiplatform)

📥 Supports all file types (images, videos, audio, documents, etc.)

🔄 Parallel downloads with configurable limits

⏸️ Pause, Resume, and Cancel support for individual/all downloads

📊 Download progress tracking via Flow<Int>

🔁 Retry logic, background support, and graceful error handling

🧰 Utility functions: MIME type detection, path helpers, local caching

🔐 Platform-specific file handling with shared abstraction

🧰 Architecture Overview
bash
Copy
Edit
├── shared/              # KMM shared module
│   ├── downloader/      # Core logic using Ktor, Coroutines, Flows
│   └── utils/           # Platform abstractions (e.g., file I/O)
├── androidApp/          # Android-specific integration
├── iosApp/              # iOS-specific integration (SwiftUI)
Shared Module: Core logic using Ktor Client, Kotlinx Coroutines, Flow, and serialization.

Android Module: Jetpack Compose UI, WorkManager, ForegroundService for background downloads.

iOS Module: SwiftUI view, background downloads via NSURLSession.

🏗️ Key Components
DownloaderManager (shared module)
Handles:

kotlin
Copy
Edit
startDownload(fileUrl: String)
pauseDownload(id: String)
resumeDownload(id: String)
cancelDownload(id: String)
getProgress(id: String): Flow<Int>
FileSystemHelper (expect/actual pattern)
Helps with:

Safe download directory path access

Directory creation & file naming

iOS/Android specific handling via Kotlin Native interop

🧪 Testing Plan
✅ Unit testing of shared logic via kotlin-test

✅ Integration tests using mock endpoints

✅ Stress testing for multiple concurrent downloads

🔍 Performance profiling (battery, memory, thread usage)

🚀 Getting Started
Android
kotlin
Copy
Edit
val downloader = FileDownloader(HttpClientFactory().create())
val result = downloader.downloadFile("https://example.com/sample.jpg", "sample.jpg")
Use Jetpack Compose UI to bind file path and show preview.

iOS
Import shared.framework in Xcode, then:

swift
Copy
Edit
let downloader = FileDownloader(client: HttpClientFactory().create())
downloader.downloadFile(url: "https://example.com/sample.jpg", fileName: "sample.jpg") { path in
    print("Saved to: \(path)")
}
SwiftUI View shows preview after file downloads.

🔄 Roadmap
 Basic file download (iOS + Android)

 MVC Architecture

 Progress bar via Flow

 Parallel downloads with max concurrency

 Pause / Resume / Retry support

 Cache with SQLDelight or file metadata

 Full unit + integration test suite

🤝 Contribution
We welcome PRs, suggestions, and discussions. Please open an issue if you’d like to contribute.

🧑‍💻 Author
Vikas Soni
Experienced Android Engineer & Cross-platform enthusiast

📄 License
MIT License - free for commercial and personal use.
