pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("com\\.google\\.dagger.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        plugins {
            id("com.android.application") version "7.4.2" apply false
            id("org.jetbrains.kotlin.android") version "1.9.23" apply false
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SelfProtectionApp"
include(":app")
