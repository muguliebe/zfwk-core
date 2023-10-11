# zfwk-core
framework core by spring boot

## implementation zfwk-core
```shell
    maven {
        url = uri("https://nexus.egstep.com/repository/maven-public/")
    }
```

## need origin project's build.gradle
```groovy
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-noarg")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
```
