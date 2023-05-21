import com.linecorp.support.project.multi.recipe.configureByLabels

plugins {
    id("io.spring.dependency-management") version "1.1.0" apply false
    id("org.springframework.boot") version "3.0.4" apply false
    id("io.freefair.lombok") version "6.4.1" apply false
    id("com.linecorp.build-recipe-plugin") version "1.1.1" // 모듈 컨피그

    kotlin("jvm") version "1.7.22" apply false
    kotlin("kapt") version "1.7.22" apply false
    kotlin("plugin.spring") version "1.7.22" apply false
    kotlin("plugin.jpa") version "1.7.22" apply false
}

allprojects {
    group = "kdohyeon.crypto.labs"
    version = "1.0-SNAPSHOT"
}

subprojects {
    apply(plugin = "idea")
}

configureByLabels("java") {
    apply(plugin = "org.gradle.java")
    apply(plugin = "io.spring.dependency-management")

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.0.4")
            mavenBom("com.google.guava:guava-bom:31.1-jre")
            mavenBom("org.jetbrains.kotlin:kotlin-bom:1.7.22")
        }

        dependencies {
            dependency("org.apache.commons:commons-lang3:3.12.0")
            dependency("org.apache.commons:commons-collections4:4.4")
            dependency("commons-io:commons-io:2.11.0")
        }
    }

    repositories {
        mavenCentral()
        maven {
            url = uri("https://maven.restlet.com")
        }
        maven {
            url = uri("https://jitpack.io")
        }
    }

    dependencies {
        val implementation by configurations

        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
    }
}