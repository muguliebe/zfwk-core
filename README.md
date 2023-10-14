# zfwk-core
framework core by spring boot

## version
spring boto version "3.1.4"
io.spring.dependency-management version "1.1.3"


## implementation zfwk-core & use zfwk-bom
```kts
repositories{
    maven {
        url = uri("https://nexus.egstep.com/repository/maven-public/")
    }
    mavenCentral()

}
    
dependencies{
    implementation(platform("com.egstep:zfwk-bom:1.9"))
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
