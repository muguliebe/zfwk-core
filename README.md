# zfwk-core
framework core by spring boot

## version
spring boto version "3.1.4"
io.spring.dependency-management version "1.1.3"


## implementation zfwk-core & use zfwk-bom
```
repositories{
    maven {
        url = uri("https://nexus.egstep.com/repository/maven-public/")
    }
    mavenCentral()

}
    
dependencies{
    implementation(platform("com.egstep:zfwk-bom:1.28"))
}

```
