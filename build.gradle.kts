val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project
val h2_version: String by project

plugins {
    kotlin("jvm") version "2.0.20"
    id("io.ktor.plugin") version "2.3.12"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("org.kodein.di:kodein-di:7.19.0")
    implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:7.12.0") // Ktor용 Kodein DI 플러그인
    implementation("io.insert-koin:koin-ktor:3.4.0")
    implementation("io.insert-koin:koin-logger-slf4j:3.4.0")
    implementation("io.arrow-kt:arrow-optics:1.0.1")
    implementation("io.ktor:ktor-server-auth-jwt:2.0.0") // Ktor JWT 플러그인
    implementation("com.auth0:java-jwt:4.0.0") // JWT 생성 및 검증을 위한 라이브러리
    implementation("at.favre.lib:bcrypt:0.9.0")
    implementation("org.flywaydb:flyway-core:8.0.0") // 속성 자동화를 위한 라이브러리
}
