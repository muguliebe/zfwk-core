import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.4" // comment before publish
    id("org.jetbrains.kotlin.jvm") version "1.9.10"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.10"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.10"
    application
    `maven-publish`
    `java-library`
}

apply(plugin = "io.spring.dependency-management") // comment before publish
apply(plugin = "kotlin-jpa")
apply(plugin = "kotlin-allopen")
apply(plugin = "java")

group = "com.github.muguliebe.zfwk"
version = "0.3.2"

val springVersion = "3.1.4"
val zfwkBomVersion = "0.5.4"
val versionKotest = "5.7.2"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

// comment before publish start
tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}
// comment before publish start

dependencies {
    implementation(platform("com.github.muguliebe:zfwk-dependency:$zfwkBomVersion"))

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-parent:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-aop:$springVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator:$springVersion")
    implementation("org.springframework.boot:spring-boot-devtools:$springVersion")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-maven-allopen:1.7.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // zfwk
    implementation("com.github.muguliebe:zfwk-utils")
    testImplementation("com.github.muguliebe:zfwk-utils")

    // Test
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
    testImplementation("io.kotest:kotest-runner-junit5:$versionKotest")
    testImplementation("io.kotest:kotest-assertions-core:$versionKotest")
    testImplementation("io.kotest:kotest-property:$versionKotest")
    testImplementation("io.kotest:kotest-framework-datatest:$versionKotest")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.0")
}

sourceSets {
    getByName("main") {
        java.srcDir("src/main/kotlin")
        resources.srcDir("src/main/resources")
    }
    getByName("test") {
        java.srcDir("src/test/kotlin")
    }
}


publishing {
    publications {
        create<MavenPublication>("zfwk-core") {
            from(components["java"])
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
        }
    }
    repositories {
        maven {
            url= uri("https://jitpack.io")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    withType<ProcessResources> {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}
