# iTunesAPI
## Setup project
Make sure the android device version is 6.0+
```
defaultConfig {
  ..
  minSdk 23 //Support Android 6.0+
}
```
## App build.gradle

```groovy
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'androidx.navigation.safeargs.kotlin'
}
```
```groovy
dependencies {
    ...
    // Dagger
    implementation 'com.google.dagger:dagger:2.45'
    kapt 'com.google.dagger:dagger-compiler:2.45'

    // Room components
    implementation "androidx.room:room-ktx:2.5.2"
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    kapt "androidx.room:room-compiler:2.5.2"
    implementation "androidx.room:room-rxjava3:2.5.2"
    implementation "androidx.room:room-rxjava2:2.5.2"

    // rxKotlin
    implementation 'io.reactivex.rxjava2:rxjava:2.2.9'
    implementation 'io.reactivex.rxjava3:rxandroid:3.0.1'
    implementation "io.reactivex.rxjava3:rxkotlin:3.0.1"

    //Gson
    implementation 'com.google.code.gson:gson:2.9.0'

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0") {
        exclude group: "com.android.support"
    }

    //Paging3
    implementation "androidx.paging:paging-runtime:3.2.1"

    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.9.1'

    //Exoplayer
    implementation 'com.google.android.exoplayer:exoplayer:2.19.1'
}
```
##Paging
