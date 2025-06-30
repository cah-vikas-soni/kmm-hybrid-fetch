//
//  FileDownloadView.swift
//  iosApp
//
//  Created by Vikas Soni on 29/06/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct FileDownloadView: View {
    @StateObject private var service = FileDownloadService()
    @State private var url: String = ""
    @State private var downloadedImage: UIImage? = nil

    var body: some View {
        VStack(spacing: 20) {
            Text("File Downloader")
                .font(.headline)

            TextField("Enter image URL", text: $url)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal)

            Button("Download") {
                downloadedImage = nil
                service.download(url: url)
            }
            .disabled(url.isEmpty)

            if service.isLoading {
                ProgressView("Downloading...")
            }

            if let error = service.error {
                Text("❌ \(error)")
                    .foregroundColor(.red)
            }

            if !service.downloadPath.isEmpty {
               
                Text("Downloaded to: \(service.downloadPath)")
                    .font(.caption)
                    .multilineTextAlignment(.center)
                    .padding()
                
                if let image = downloadedImage {
                    Image(uiImage: image)
                        .resizable()
                        .scaledToFit()
                        .frame(height: 250)
                        .cornerRadius(12)
                        .shadow(radius: 5)
                    } else {
                        Text("Image downloaded, tap below to preview.")
                            .foregroundColor(.secondary)
                        
                        Button("Load Image") {
                            if FileManager.default.fileExists(atPath: service.downloadPath) {
                                downloadedImage = UIImage(contentsOfFile: service.downloadPath)
                            }
                        }
                    }
        
            }
        }
        .padding()
        .onChange(of: service.downloadPath) { newPath in
            // Auto-load image if valid
            if newPath.lowercased().hasSuffix(".jpg") || newPath.lowercased().hasSuffix(".png") {
                if FileManager.default.fileExists(atPath: newPath) {
                    downloadedImage = UIImage(contentsOfFile: newPath)
                }
            }
        }
    }
}
