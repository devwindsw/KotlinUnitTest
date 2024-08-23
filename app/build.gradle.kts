plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.devwindsw.kotlinunittest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.devwindsw.kotlinunittest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    testOptions {
        packaging {
            //Issue: files found with path 'META-INF/LICENSE.md
            //Reference: https://stackoverflow.com/questions/75150167/instrumented-tests-will-not-run-6-files-found-with-path-meta-inf-license-md
            resources.excludes.add("META-INF/*")
            jniLibs {
                //Issue: Unable to dlopen libmockkjvmtiagent.so: dlopen failed: library "libmockkjvmtiagent.so" not found
                //Reference: https://stackoverflow.com/questions/73146494/not-able-to-use-mockk-in-android-espresso-ui-testing
                useLegacyPackaging = true
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Issue: Mockk configuration
    //Reference: https://mockk.io/ANDROID.html
    testImplementation("io.mockk:mockk:1.13.12")
    androidTestImplementation("io.mockk:mockk-android:1.13.12")

    //Issue: Coroutines
    //Reference: https://blog.logrocket.com/unit-testing-kotlin-projects-with-mockk-vs-mockito/#mockk-vs-mockito
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0-RC.2")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0-RC.2")

    //Issue: Didn't find class "kotlinx.coroutines.DelayWithTimeoutDiagnostics
    //Reference: https://issuetracker.google.com/issues/323394096#comment3
    //Reference: https://github.com/Kotlin/kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC.2")
}