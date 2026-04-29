name: Build FishNet APK

on:
  push:
    branches: [ main ]
  workflow_dispatch:  # Manuel tetikleme butonu

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Kodu al
        uses: actions/checkout@v4

      - name: Java 17 kur
        uses: actions/setup-java@v4
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Android SDK kur
        uses: android-actions/setup-android@v3

      - name: Gradle izni ver
        run: chmod +x gradlew

      - name: Gradle wrapper oluştur
        run: |
          gradle wrapper --gradle-version 8.4 || true
          chmod +x gradlew

      - name: Debug APK derle
        run: ./gradlew assembleDebug --no-daemon

      - name: APK'yı kaydet (indir buradan!)
        uses: actions/upload-artifact@v4
        with:
          name: FishNet-APK
          path: app/build/outputs/apk/debug/app-debug.apk
          retention-days: 30
