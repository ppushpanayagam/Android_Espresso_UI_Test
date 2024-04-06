buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}

android {
    namespace = "com.example.testapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.testapplication"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunnerArguments ["clearPackageData"] =  "true"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    packaging {
        resources.excludes.add("MANIFEST.MF")
        resources.excludes.add("META-INF/LICENSE")
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/LICENSE.md")
        resources.excludes.add("META-INF/LICENSE-notice.md")
        resources.excludes.add("META-INF/MANIFEST.MF")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("META-INF/rxjava.properties")
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        execution = "ANDROIDX_TEST_ORCHESTRATOR"

        packaging {
            jniLibs {
                useLegacyPackaging = true
            }
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    api("com.michael-bull.kotlin-result:kotlin-result:1.1.18")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.dagger:hilt-android:2.48.1")

    kapt("com.google.dagger:hilt-android-compiler:2.48.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor("com.github.bumptech.glide:compiler:4.15.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")
    implementation("com.squareup.moshi:moshi:1.15.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    val mviVersion = "3.3.0"
    implementation("com.arkivanov.mvikotlin:mvikotlin:$mviVersion")
    implementation("com.arkivanov.mvikotlin:mvikotlin-main:$mviVersion")
    implementation("com.arkivanov.mvikotlin:mvikotlin-logging:$mviVersion")
    implementation("com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines-android:$mviVersion")
    implementation("com.arkivanov.mvikotlin:rx:$mviVersion")
    implementation("com.arkivanov.mvikotlin:rx-internal:$mviVersion")

    debugImplementation("androidx.fragment:fragment-testing:1.6.2")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.10")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    // AndroidX Test libraries - for unit tests, simulates Android Framework components (Application, Activity)
    /* Steps to properly setup AndroidX Test:
    1. add AndroidX Test core and ext dependencies
    2. add Roboelectric Testing Lib dependency
    3. Annotate the class with AndroidJunit4 test runner
    4. Write Androidx Test code
    */
    // AndroidX Test Core, Ext and Truth
    testImplementation("androidx.test:core-ktx:1.6.0-alpha04")
    testImplementation("androidx.test.ext:junit-ktx:1.1.5")
    testImplementation("androidx.test.ext:truth:1.6.0-alpha02")

    // Robolelectric for simulating Android environment
    testImplementation("org.robolectric:robolectric:4.11.1")

    // Hilt testing
    testImplementation("com.google.dagger:hilt-android-testing:2.47")
    kaptTest("com.google.dagger:hilt-android-compiler:2.48.1")

    // Turbine lib for flow testing and assertion
    testImplementation("app.cash.turbine:turbine:1.0.0")

    // Mockito for creating mocks and templates
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")

    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.48.1")

    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.48.1")

    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.5.1")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48.1")
    androidTestImplementation("io.mockk:mockk:1.13.8")
    androidTestImplementation("io.mockk:mockk-android:1.13.8")
    androidTestImplementation("org.hamcrest:hamcrest:2.2")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestUtil("androidx.test:orchestrator:1.4.2")
}