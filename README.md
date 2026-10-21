# Nicequest login tests

## What this is
This repository contains an automated test suite for the Nicequest login, built with Java, Cucumber and Appium.

## Status
Work in progress. The code is written but has not yet been run end to end on a device.

## Project structure
- **Config**: Select the platform and create the Driver according to the OS.
- **Pages**: Classes with attributes and functions for the page tested.
- **Steps**: Scripts where the test steps are developed.
- **Runners**: Set up for Cucumber.
- **Feature**: All the scenarios in Gherkin language. 

## Prerequisites

### Software
Exact versions used are listed in Test Environment.
 - JDK 21 -> `java --version`
 - Maven -> `mvn --version`
 - Node.js -> `node -v`
 - Appium -> `appium --version`
 - UiAutomator2 -> `appium driver list --installed`
- Android SDK Platform-Tools (`adb`) -> `adb --version`, with the `ANDROID_HOME` environment variable set

### Device
- An Android device with **USB debugging** enabled, listed as `device` by `adb devices`.
- Xiaomi/HyperOS devices also need **Install via USB** and **USB debugging (Security settings)** enabled.
- Nicequest installed from Google Play and logged out.

### Credentials
The tests read a test account from environment variables, so no credentials are stored in the repository:
```
setx NICEQUEST_EMAIL "your-test-email"
setx NICEQUEST_PASSWORD "your-test-password"
```
Open a new terminal afterwards so the variables are loaded.

### Appium server
Start it before running the tests and keep it running (default port 4723):
```
appium
```

### iOS
Running on iOS requires macOS with Xcode and the XCUITest driver. In this project iOS is only configured (`ios.properties`) and has not been run.
## Test Environment

|Tools and Devices  |Name and Version   |
| :---------------- | :---------------: |
|App under test    |Nicequest         |
|Automation Tool   |Appium 3.8.0      |
|Platform Driver |UiAutomator2 8.7.0 |
|Java Development Kit |JDK 21 (LTS) |
|IDE |IntelliJ IDEA 2026.2.3 |
|Project manager |Maven 3.10.0 |
|Software Development Kit |Android SDK Platform-Tools 37.0.1 |
|Device OS|Xiaomi HyperOS 2.0.4.0 Android 14 |
|Development Machine OS |Windows 10 |
|Element Inspection Tool |Appium Inspector 2026.9.2|
|Libraries |java-client 11.0.0 <br> Cucumber 8.0.4 <br> JUnit 6.1.3|
|Runtime Environment |Node.js 24.22.0 |


## How to run

1. Connect the device and check it is listed: `adb devices`
2. Start the Appium server in a separate terminal and keep it running: `appium`
3. From the project root, run the tests for the chosen platform:

```
mvn test -Dplatform=android
mvn test -Dplatform=ios
```

The platform is read from the `platform` system property (default: `android`) and selects the matching file in `src/test/resources/config`. Running on iOS requires macOS with Xcode (see Prerequisites).

> Status note: these commands have not yet been verified on a device.

## Design decisions and trade-offs

### Environment
- **Real device instead of an emulator.** This decision was made since the development machine has limited RAM.

### Device-specific notes (Xiaomi / HyperOS)
- The capabilities `skipDeviceInitialization` and `ignoreHiddenApiPolicyError` were needed to start a session on this device, plus the Xiaomi developer options listed in Prerequisites. Other devices may not need them.

## What I would do with more time

- Run the suite on iOS.
- Add reporting and screenshots on failure.
- Reset the app state or log out between scenarios, and add a sign-out scenario.
- Cover more negative cases like: Invalid email format or forgot password link.

## AI Usage
Claude (Sonnet 5.5) was used as a learning guide: to explain new concepts
(Java, Cucumber, Appium), to walk me through the tool installation, and to
review my code. I wrote the code and made the design decisions myself,
and I can explain each of them.