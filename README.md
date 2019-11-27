# room_rxJava_Retrofit_phpLocalServer
Ejemplo aplicando AndroidX, RXJava, Retrofit, Room

## Paginas de ejemplo y refuerzo de lectura
* https://medium.com/@ajaysaini.official/building-database-with-room-persistence-library-ecf7d0b8f3e9
* https://medium.com/mindorks/using-room-database-with-livedata-android-jetpack-cbf89b677b47
* https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
* https://medium.com/androiddevelopers/room-rxjava-acb0cd4f3757
* https://medium.com/mindorks/room-implementation-with-rxjava-746f8ba39d19
* https://medium.com/mindorks/using-room-database-android-jetpack-675a89a0e942
* https://android.jlelse.eu/sqlite-on-android-made-simple-room-persistence-library-with-a-touch-of-rxjava-55e8dc5301cf

## Librerias utilizadas
```javascript
    implementation 'androidx.appcompat:appcompat:1.1.0'
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    implementation('com.github.ihsanbal:LoggingInterceptor:3.0.0') {
        exclude group: 'org.json', module: 'json'
    }

    // Room components
    implementation "androidx.room:room-runtime:2.2.2"
    annotationProcessor "androidx.room:room-compiler:2.2.2"
    androidTestImplementation "androidx.room:room-testing:2.2.2"
    debugImplementation 'com.amitshekhar.android:debug-db:1.0.6'

    //RXJAVA
    implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.4.0'
    implementation "android.arch.persistence.room:rxjava2:1.1.1"
    androidTestImplementation "android.arch.core:core-testing:1.1.1"
```
