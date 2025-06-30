import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "2.1.20"
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    val iosTargets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    iosTargets.forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
       commonMain.dependencies {
                implementation(libs.io.ktor.client.core)
                implementation(libs.jetbrains.kotlinx.coroutines.core)
                implementation(libs.runtime)
                implementation(libs.jetbrains.kotlinx.serialization.json)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.okio)
                implementation(libs.kotlinx.datetime)
        }

        androidMain.dependencies {
                implementation(libs.ktor.client.okhttp)
                implementation(libs.sqldelight.android.driver)
        }

        iosMain.dependencies{
                implementation(libs.ktor.client.darwin)
                implementation(libs.sqldelight.native.driver)
        }

        commonTest.dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutines.test)
        }
    }
}

android {
    namespace = "com.vikas.hybrid_file_download_manager"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
