plugins {
    kotlin("js") version "1.4.32"

    // https://github.com/gciatto/kt-npm-publish
    id("io.github.gciatto.kt-npm-publish") version "0.3.6"
}

group = "org.hydev"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-nodejs:0.0.7")
}

kotlin {
    js(IR) { // https://discuss.kotlinlang.org/t/kotlin-js-1-4-how-to-output-a-npm-package/19128
        binaries.executable()

        // https://www.kotlincn.net/docs/reference/js-modules.html
        useCommonJs()
        nodejs {

        }
    }
}

npmPublishing {
    val npmToken = properties["npmToken"] ?: ""
    token.set(npmToken as String)
}
