import io.github.gciatto.kt.node.Bugs
import io.github.gciatto.kt.node.People

plugins {
    kotlin("js") version "1.4.32"

    // https://github.com/gciatto/kt-npm-publish
    id("io.github.gciatto.kt-npm-publish") version "0.3.6"
}

group = "org.hydev"
version = "0.7.0" // https://semver.org/lang/zh-CN/

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

        // http://www.kotlincn.net/docs/reference/js-project-setup.html#packagejson-customization
        nodejs {
            compilations["main"].packageJson {
                // https://docs.npmjs.com/cli/v7/configuring-npm/package-json#bin
                customField("bin", mapOf("lkf" to "./kotlin/looks-fun.js"))
            }
        }
    }
}

val github = "https://github.com/Vanilla-s-Lab/looks-fun"
val (myName, myEmail) = "Vanilla" to "neko@hydev.org"
val myGithub = "https://github.com/VergeDX"
npmPublishing {
    val npmToken = properties["npmToken"] ?: ""
    token.set(npmToken as String)

    // https://github.com/gciatto/kt-npm-publish
    liftPackageJson { // Hook in liftJsSources is too early, .js will be overwrite.
        val pjName = project.name
        val mainJs = file("${buildDir.path}/js/packages/$pjName/kotlin/${pjName}.js")
        val bakByteArray = mainJs.readBytes()

        // https://docs.npmjs.com/cli/v7/configuring-npm/package-json#bin
        val shebang = "#!/usr/bin/env node" + "\n\n"
        mainJs.writeBytes(shebang.toByteArray() + bakByteArray)

        // https://docs.npmjs.com/cli/v7/configuring-npm/package-json
        description = "A CLI tool to lookup 43 kinds of Java Standard Functional Interface."
        keywords = listOf("Java", "Kotlin").toMutableList()
        homepage = github
        bugs = Bugs("$github/issues", myEmail)
        license = "MIT"
        people = listOf(People(myName, myEmail, myGithub)).toMutableList()
    }
}
