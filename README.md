<img src="https://raw.githubusercontent.com/ShiftHackZ/NTFY-Interceptor-Android/refs/heads/master/docs/assets/ntfy.png" width="100" />

# NTFY Interceptor Android

⚠️ **This project is in alpha stage.** Use at your own risk.

## 🔥 What is this?

**NTFY Interceptor** is an experimental Android application designed to **capture notifications from all apps** on one device and forward them to a self-hosted [`ntfy`](https://ntfy.sh) server via HTTP — so another device (typically hardened with no Google Services) can receive those notifications securely.

This app is useful in setups where push delivery must be decoupled from Google Firebase and similar proprietary services, such as:

- 📱 Primary phone with full app ecosystem (Google Play, WhatsApp, etc.)
- 🔒 Secondary phone running [GrapheneOS](https://grapheneos.org/) or similar, with **no Google services**, receiving only selected push messages via `ntfy`.

## 🧠 Use Case

> Imagine you want a hardened, privacy-first phone with **no Google**, but still need push notifications from selected apps.  
With `NTFY Interceptor` installed on a secondary device (e.g. Pixel 3 XL with root access), you can forward all system notifications to a custom `ntfy` endpoint.

Then, a **trusted GrapheneOS phone** can subscribe to that endpoint and receive **minimal, real-time, secure push notifications** — without exposing itself to Google, FCM, or untrusted networks.

## 💡 Features

- 📲 Captures all app notifications via `NotificationListenerService`
- 🔁 Forwards notifications as JSON payloads to a user-defined `ntfy` server (supports custom base URL and Basic Auth)
- 🔧 Stores settings (base URL, username, password) securely via [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
- 🔌 Built-in Foreground Service to ensure delivery even under background restrictions
- 🧠 Designed with `Koin` for DI and `Ktor` for networking
- 🧪 Lightweight UI for testing and configuration
- ✅ Compatible with self-hosted `ntfy` servers using HTTP Basic Authentication

## 🚀 How It Works

1. App runs as a **foreground service** to ensure it stays alive
2. A **NotificationListenerService** observes all system notifications
3. When a new notification is posted:
    - It extracts relevant data (title, text, app package, etc.)
    - Sends it as a `POST` request to your configured `ntfy` endpoint
4. On another device (e.g. GrapheneOS), the user subscribes to that topic via the `ntfy` app

## 🔒 Security Notes

- Credentials and base URL are stored in `DataStore` (not plaintext files)
- Communication is expected to be over HTTPS — **reverse proxy with valid TLS (e.g. Cloudflare or Caddy) is strongly recommended**
- Only your trusted GrapheneOS device should subscribe to the topic

## 🛠 Requirements

- Android 12.0+ (API 31+)
- Device **must allow notification access**
- Foreground service permissions enabled
- Internet access
Optional (but useful):
- Root access (to auto-start after boot)
- [Magisk](https://github.com/topjohnwu/Magisk) for more advanced automation
- Static IP / DDNS + TLS via Cloudflare / Nginx / HAProxy for public exposure

## ⚙️ Configuration

- Base URL: Full URL to your ntfy server (e.g. `https://ntfy.moroz.cc`)
- Username / Password: Your HTTP Basic Auth credentials (or leave empty for anonymous access)
- Port forwarding / TLS should be handled externally via reverse proxy (e.g. HAProxy or nginx)

## 🛠 Setup

To properly configure the app on your main (notification forwarding) device:

- Launch the app and tap the Setup Permissions button.
- In the system settings screen that opens, enable "Allow notification access" for this app.
- Open system App Info > Permissions, and ensure:
  - Notifications are allowed
  - Battery is set to "Unrestricted" (not optimized)

This ensures the app can run continuously in the background and forward all notifications without interference.

## 🚧 Current Limitations

- No filtering or whitelisting (all notifications are forwarded)
- No end-to-end encryption (relies on HTTPS + auth)
- No retry queue if offline
- UI is minimal and intended for debug/config

## 📦 Build Instructions

```bash
git clone https://github.com/ShiftHackZ/NTFY-Interceptor-Android
cd NTFY-Interceptor-Android
./gradlew assembleDebug
adb install app/build/outputs/apk/debug/app-debug.apk
```

Or open in Android Studio and run.

## Screenshots

<img src="https://raw.githubusercontent.com/ShiftHackZ/NTFY-Interceptor-Android/refs/heads/master/docs/assets/Screenshot_20250712_203606.png" width="400" />

## 📜 License
MIT — see LICENSE file.

> Made with ❤️ by @ShiftHackZ — Feedback & PRs welcome!
