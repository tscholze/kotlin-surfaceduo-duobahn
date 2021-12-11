import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// MARK: - Properties -

var ktorVersion = "1.6.7"

group = "com.github.tscholze.duobahn"
version = "0.1-SNAPSHOT"

// MARK: - Application -

application {
    mainClass.set("MainKt")
}

// MARK: - Plugins -

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.31"
    application
}

// MARK: - Repositories -

repositories {
    mavenCentral()
}

// MARK: - Dependencies -

dependencies {
    // Ktor
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation ("ch.qos.logback:logback-classic:1.2.7")
    // Testing
    testImplementation(kotlin("test"))
}

// MARK: - Tasks -

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}