import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposedVer = "0.23.1"
val koDeInVer= "6.5.5"

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
    languageVersion = "1.3"
}

plugins {
    application
    kotlin("jvm") version "1.3.70"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "hu.bongyor"
version = "0.0.1"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-locations:$ktor_version")
    implementation("io.ktor:ktor-gson:$ktor_version")
    implementation("org.jetbrains.exposed", "exposed-core", exposedVer)
    implementation("org.jetbrains.exposed", "exposed-dao", exposedVer)
    implementation("org.jetbrains.exposed", "exposed-jdbc", exposedVer)
    implementation("org.jetbrains.exposed", "exposed-java-time", exposedVer)
    implementation("org.kodein.di", "kodein-di-generic-jvm", koDeInVer)
    implementation("org.kodein.di", "kodein-di-framework-ktor-server-controller-jvm", koDeInVer)
    implementation("org.postgresql", "postgresql",  "42.2.12")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation ("com.h2database", "h2", "1.4.200")
}

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")