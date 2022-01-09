/**
 * iTunes
 * Class: Dependencies
 * Created by leesohyun on 2022/01/03.
 *
 * Description:
 */
object Dependencies {
    private const val kotlinVersion = "1.5.20"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"

    const val core = "androidx.core:core-ktx:1.7.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.0"
    const val material = "com.google.android.material:material:1.4.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.2"
    const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
    const val preference = "androidx.preference:preference-ktx:1.1.1"
    const val junit = "junit:junit:4.+"
    const val testJunit = "androidx.test.ext:junit:1.1.3"
    const val espresso = "androidx.test.espresso:espresso-core:3.4.0"

    // Page3: 페이징 라이브러리(데이터 특정 개수만큼 다운로드 및 캐싱할 수 있도록 도롸주는 라이브러리)
    private const val pagingVersion = "3.1.0"
    const val pagingRuntime = "androidx.paging:paging-runtime-ktx:$pagingVersion"

    // Room: 데이터를 저장하기 위한 local DB
    private const val roomVersion = "2.4.0"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val runCompiler = "androidx.room:room-compiler:$roomVersion"
    const val room = "androidx.room:room-ktx:$roomVersion"

    // Glide: 이미지 처리 (이미지 로드와 캐싱을 도와주는 라이브러리)
    private const val glideVersion = "4.12.0"
    const val glide = "com.github.bumptech.glide:glide:$glideVersion"
    const val glideCompiler = "com.github.bumptech.glide:compiler:$glideVersion"

    // Retrofit: type-safe한 HTTP 통신
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"

    // Hilt: 의존성 주입
    private const val hiltVersion = "2.40.5"
    const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:$hiltVersion"

    // Activity
    private const val activityKtxVersion = "1.4.0"
    const val activity = "androidx.activity:activity-ktx:$activityKtxVersion"

    // Fragment
    private const val fragmentKtxVersion = "1.4.0"
    const val fragment = "androidx.fragment:fragment-ktx:$fragmentKtxVersion"

    // Lifecycle
    private const val lifecycleExVersion = "2.2.0"
    private const val lifecycleVersion = "2.4.0"
    const val lifecycleExtension = "androidx.lifecycle:lifecycle-extensions:$lifecycleExVersion"
    const val viewModelLifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val livedataLifecycle = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"

    // Navigation
    private const val navigationVersion = "2.3.5"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    // Multidex
    private const val multidexVersion = "2.0.1"
    const val multidex = "androidx.multidex:multidex:$multidexVersion"
}