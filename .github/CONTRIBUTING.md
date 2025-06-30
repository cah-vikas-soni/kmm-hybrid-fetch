# 🤝 Contributing to KMM File Downloader Plugin

Thank you for considering contributing! We welcome all kinds of contributions to make this Kotlin Multiplatform File Downloader better — code, tests, docs, ideas, and feedback.

---

## 📋 Ground Rules

### ✅ What You Can Contribute
- 🚀 New features (e.g., pause/resume, batch download)
- 🐞 Bug fixes
- 🧪 Tests (unit/integration/performance)
- 🧰 Platform-specific improvements (iOS/Android)
- 📝 Documentation or demo apps
- 💡 Suggestions and improvements via GitHub Issues

---

## 📦 Project Setup

### Android
1. Open the project in **Android Studio**
2. Run `androidApp` module to test the Android UI

### iOS
1. Build the `.framework` from `shared` using:
   ```bash
   ./gradlew :shared:linkDebugFrameworkIosSimulatorArm64
   ```
2. Import it manually in the `iosApp` target
3. Open and run in **Xcode**

---

## 🔧 How to Contribute

1. **Fork** this repo
2. **Clone** your fork locally:
   ```bash
   git clone https://github.com/YOUR_USERNAME/kmm-file-downloader.git
   ```
3. Create a new branch:
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. **Write code**, add tests, and commit
5. **Push** to your fork:
   ```bash
   git push origin feature/your-feature-name
   ```
6. Open a **Pull Request** on the main repo

---

## 🔍 Code Style

- Use **Kotlin conventions** (Ktlint recommended)
- Write **descriptive commit messages**
- Keep shared code **platform-agnostic**
- Organize by platform (`androidMain`, `iosMain`) and common code (`commonMain`)
- Use `expect/actual` appropriately

---

## ✅ Pull Request Checklist

Before submitting your PR:
- [ ] Code compiles and runs on both Android & iOS
- [ ] Tests (if applicable) are added or updated
- [ ] Documentation (README, comments) is updated
- [ ] No commented-out or debug code remains
- [ ] Lint and formatting are clean

---

## 🙏 Code of Conduct

Please follow our [Code of Conduct](CODE_OF_CONDUCT.md). Be respectful, inclusive, and collaborative.

---

## 🗣️ Need Help?

- Open a GitHub [Issue](https://github.com/YOUR_REPO/issues)
- Start a discussion in the `Discussions` tab (if enabled)
- Contact the maintainer directly

---

Thanks for being awesome! 💙

