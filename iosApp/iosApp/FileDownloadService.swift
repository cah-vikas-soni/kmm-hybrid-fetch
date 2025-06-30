//
//  FileDownloadService.swift
//  iosApp
//
//  Created by Vikas Soni on 29/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared

class FileDownloadService: ObservableObject {
    private let downloader: FileDownloader

    @Published var downloadPath: String = ""
    @Published var isLoading: Bool = false
    @Published var error: String?

    init() {
        self.downloader = FileDownloader(client: HttpClientFactory().create())
    }

    func download(url: String) {
        isLoading = true
        error = nil

        let fileName = URL(string: url)?.lastPathComponent ?? "file.dat"

        Task {
            do {
                let path = try await downloader.downloadFile(url: url, fileName: fileName)
                DispatchQueue.main.async {
                    self.downloadPath = path
                    self.isLoading = false
                }
            } catch {
                DispatchQueue.main.async {
                    self.error = error.localizedDescription
                    self.isLoading = false
                }
            }
        }
    }
}
