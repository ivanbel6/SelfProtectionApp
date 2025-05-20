# 🛡️ SelfProtectionApp

A mobile application to protect users from social engineering attacks through **AI-powered message analysis**, **behavioral monitoring**, and **educational tools**.

---

## 📖 Overview

**SelfProtectionApp** is an Android application designed to protect users from social engineering attacks (e.g., phishing), combining artificial intelligence, behavioral analytics, and digital literacy training.

Built with **Kotlin** and **Jetpack Compose**, the app analyzes messages from **VK**, **Telegram**, and **Mail.ru**, monitors behavioral anomalies, and provides **interactive educational modules**. 

> 🧠 Achieves 92% phishing detection accuracy, consumes ~3.5% battery/hour, and scores 82/100 on the System Usability Scale (SUS).

---

## ✨ Features

- **🧠 AI-Powered Message Analysis**  
  Collects 5 recent messages/emails from VK, Telegram, and Mail.ru → forms a single JSON → analyzes via AI (e.g., xAI API) → detects phishing → notifies with low/medium/high threat levels.

- **📊 Behavioral Analytics**  
  Monitors user clicks and text input via AccessibilityService to detect risky behavior patterns and issue real-time warnings.

- **🎓 Educational Module**  
  Interactive training with local progress tracking to improve digital literacy.

- **🔔 Real-Time Notifications**  
  Delivers actionable alerts like _“Avoid links”_ when threats are detected.

- **⚡ Efficient Design**  
  Jetpack Compose UI, <5% battery/hour, Android 7.0+ compatibility, and 82/100 usability score.

- **🔐 Secure Storage**  
  Uses Room DB with encrypted tables (`threats`, `training_progress`, `behavior_logs`).

- **📬 Multi-Platform Integration**  
  Fully supports VK (via VK SDK), Telegram (via TDLib), and Mail.ru (via IMAP).

---

## 🛠️ Architecture

The app follows **Clean Architecture** with well-defined layers:

### 🧩 Layers

- **Presentation Layer**  
  UI built with Jetpack Compose: screens for stats, training, notifications, and permissions.  
  Design: Material palette (`#FF5722`, `#4CAF50`), Roboto font.

- **Domain Layer**  
  Business logic through use cases like `AnalyzeMessageUseCase`.

- **Data Layer**  
  Room DB for threats, training progress, and behavioral logs.  
  Integrates VK SDK, TDLib, IMAP clients.

- **AI Integration**  
  Messages → JSON → AI API → Regex parse → User alerts.

- **Behavioral Monitoring**  
  Uses AccessibilityService to track anomalies (e.g., fast clicking, excessive input).

### 📊 Diagrams (located in `docs/architecture/`):

- **IDEF0**: Message analysis pipeline  
- **UML**: Core classes  
- **ER Diagram**: DB schema  
- **Use Case Diagram**: User interactions  
- **Activity Diagram**: Message analysis flow

---

## 📈 Performance

| Metric                       | Value                                      |
|-----------------------------|--------------------------------------------|
| Phishing Detection Accuracy | **92%** (500 messages: 200 phishing)       |
| False Positives             | **2%**                                     |
| Battery Usage               | **3.5%/hour** (Samsung A52, 3000 mAh)      |
| Response Time               | **1.3s** (message → notification)          |
| Memory Usage                | **~120MB** (50 users)                      |
| Database Size               | **~6MB** (100 users, 10,000 logs)          |

### ⏱ Computational Complexity

- **AI analysis**: `O(n * k)` → n: JSON length (~2500 chars)  
- **JSON creation**: `O(n)`  
- **Regex parsing**: `O(m)` (~1000 chars)  
- **Behavioral events**: `O(1)` per event

### 💾 Storage Complexity

- `threats`: `O(t)` (≤1000 entries ≈ 1MB)  
- `training_progress`: `O(u)` (≤100 users ≈ 0.1MB)  
- `behavior_logs`: `O(l)` (≤10,000 events ≈ 5MB)

---

## 🚀 Getting Started

### ✅ Prerequisites

- **Android Studio**: 2023.3.1 or newer  
- **Kotlin**: 1.9.0  
- **JDK**: 17  
- **Android device/emulator**: Android 7.0+ (API 24)  
- **API Keys**:  
  - VK: [VK Developers](https://vk.com/dev)  
  - Telegram: [Telegram API](https://core.telegram.org/tdlib)  
  - Optional: xAI API

🛠️ Build the Project
Open the project in Android Studio

Sync Gradle files

Build the project via:
Menu → Build → Make Project

▶️ Run the App
Connect your device or start an emulator

Click Run > Run 'app'

📦 Dependencies
Key libraries and tools used:

Library	Version
Kotlin	1.9.0
Jetpack Compose	1.5.0
Room	2.5.0
VK SDK	3.0.1
TDLib	1.8.0
Kotlinx Serialization	1.5.0
OkHttp	4.10.0
Hilt (DI)	2.44

📄 Full list of dependencies: see build.gradle

🧪 Testing
The app includes multiple testing layers:

✅ Unit Tests: Core logic (AnalyzeMessageUseCase) with JUnit, MockWebServer

🧪 UI Tests: Navigation & notifications with Espresso

📉 Performance Tests: Battery & memory with Android Profiler, Battery Historian

▶️ Run Tests
# Run unit tests
./gradlew test

# Run UI tests on connected device/emulator
./gradlew connectedAndroidTest
📈 Usage Guide
Launch App → Grant required permissions (Accessibility, Notifications)

Authenticate → Sign in to VK, Telegram, and Mail.ru

Monitor → App analyzes recent messages (AI)

Get Notified → Threat alerts with actionable recommendations

Train → Complete digital literacy modules

Track → View threat history and training progress

🤝 Contributing
Contributions are welcome! Follow these steps:

Fork the repository

Create a feature branch
git checkout -b feature/your-feature
Use Conventional Commits for clarity
Test your changes
./gradlew test
Submit a Pull Request with a descriptive summary

💡 Please review the Code of Conduct before contributing.

📜 License
This project is licensed under the MIT License.
You’re free to use, modify, and distribute — just keep the license notice intact.

📬 Contact
Author: [Your Name]

Email: your.email@example.com

Issues: GitHub Issues

Website: Coming soon

🙏 Acknowledgments
xAI: For AI-based phishing detection inspiration

JetBrains & Google: Kotlin, Jetpack Compose

Open-Source Community: TDLib, VK SDK

Research Sources: Verizon (2024), IBM (2023)

🛡️ Protect yourself with SelfProtectionApp — stay safe, stay informed.
