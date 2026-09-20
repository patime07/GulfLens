# GulfLens

GulfLens is a Kotlin Multiplatform market-entry assessment for comparing Dubai and Abu Dhabi. The same Compose UI and scoring engine run on Android, Web/Wasm, and Desktop.

## What is shared

All GulfLens product code is in `shared/src/commonMain/kotlin/com/nyuad/gulflens`:

- `ui/` — the responsive Compose screens, theme, and reusable components
- `presentation/` — the six-stage assessment state machine
- `domain/` — entry-route rules and weighted comparison engine
- `data/` — companies, products, startup options, and benchmark inputs
- `i18n/` — English, Simplified Chinese, Arabic, and RTL metadata

The Android launcher in `androidApp` remains intentionally small and calls the shared `App()` composable. The HTML prototype is a design reference only; the Android app does not use a WebView.

## Run on the Pixel 7 emulator

1. Open this `GulfLens` folder in IntelliJ IDEA.
2. Wait for Gradle sync and indexing to finish.
3. Start the Pixel 7 from Device Manager if it is not already running.
4. In the top toolbar, select the `androidApp` run configuration.
5. Select `Pixel 7` as the device.
6. Click the green Run triangle.

If IntelliJ asks for an SDK path after opening this copy, select the Android SDK already installed on your Mac (normally `~/Library/Android/sdk`).

## Command-line checks

From the project root:

```bash
./gradlew :shared:jvmTest
./gradlew :androidApp:assembleDebug
```

Optional Desktop check:

```bash
./gradlew :desktopApp:run
```

## Run in a web browser

From the project root, run:

```bash
./gradlew :shared:wasmJsBrowserDevelopmentRun
```

Gradle starts a development server and normally opens the app automatically. If it does not, open the localhost URL printed in the Run window (typically `http://localhost:8080`). Keep that Gradle task running while demonstrating the app; stop it with `Control+C`.

To create static production files for deployment:

```bash
./gradlew :shared:wasmJsBrowserDistribution
```

The generated site is written under `shared/build/dist/wasmJs/productionExecutable`.

## Current assessment flow

1. Landing page
2. Existing-company or startup track
3. Company selection or startup business profile
4. Product/entry route or startup planning ranges
5. Animated five-group analysis
6. Hard weighted score and separate soft market-sentiment comparison

The current score engine intentionally preserves the prototype assumptions: the selected entry route changes the metric weights; the other answers provide scenario context but do not yet change the underlying benchmark values. Treat the results as curated demo assumptions, not a forecast or professional advice.

## Company profile references

Company descriptions and locations were checked against official sources on 20 September 2026:

- [CAVA investor overview](https://investor.cava.com/overview/default.aspx)
- [Warby Parker investor overview](https://investors.warbyparker.com/overview/)
- [Freshpet annual report](https://investors.freshpet.com/static-files/b2292e19-f658-4554-86d0-9e07f1d2a0c5)
- [Toast annual report](https://www.sec.gov/Archives/edgar/data/1650164/000165016426000097/toastinc10k2026v1-final.pdf)

## Platform structure

Android has a thin launcher in `androidApp`, Desktop has a thin launcher in `desktopApp`, and Web/Wasm has its launcher and HTML shell in `shared/src/wasmJsMain`. All product behavior remains in `shared/src/commonMain`.
