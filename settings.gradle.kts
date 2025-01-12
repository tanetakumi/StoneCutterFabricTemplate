pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.5.1"
}

stonecutter {
    kotlinController = true
    centralScript = "build.gradle.kts"

    shared {
        versions("1.18.2","1.19.4","1.20.1", "1.20.4", "1.20.6", "1.21.1")
    }
    create(rootProject)
}

rootProject.name = "StoneCutterFabricTemplate"