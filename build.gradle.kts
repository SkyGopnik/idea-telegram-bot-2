import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val exposedVersion: String by project
val telegramBotVersion: String by project

plugins {

    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"

    application

}

group = "org.pixefy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {

    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:$telegramBotVersion")

    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-kotlin-datetime:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-json:$exposedVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("org.postgresql:postgresql:42.5.1")

    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}

application {
    mainClass.set("dev.skgopnik.ideaTelegramBot.MainKt")
}
