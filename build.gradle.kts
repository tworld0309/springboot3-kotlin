import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	dependencies {
		classpath("gradle.plugin.com.ewerk.gradle.plugins:querydsl-plugin:1.0.10")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.22")
		classpath("org.jetbrains.kotlin:kotlin-allopen:1.7.22")
		classpath("org.jetbrains.kotlin:kotlin-noarg:1.7.22")
	}
}


plugins {
	val kotlinPluginVersion = "1.7.22"

	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version kotlinPluginVersion
	kotlin("plugin.spring") version kotlinPluginVersion
	kotlin("plugin.jpa") version kotlinPluginVersion // apply false
	kotlin("kapt") version "1.7.22"
	idea
}


group = "com"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

allOpen {
	// Spring Boot 3.X.X
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

noArg {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}


dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	// hikari - connection pool 보완 (enterprise 환경용)
	implementation("com.mysql:mysql-connector-j")
	implementation("org.bgee.log4jdbc-log4j2:log4jdbc-log4j2-jdbc4.1:1.16")


	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// jpa
	// Spring Boot 3.X.X
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.5")
	implementation("com.vladmihalcea:hibernate-types-60:2.21.1")
	implementation("com.infobip:infobip-spring-data-jpa-querydsl-boot-starter:8.1.1")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

	// webclient
	implementation("io.netty:netty-resolver-dns-native-macos:4.1.79.Final:osx-aarch_64")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


idea {
	module {
		val kaptMain = file("build/generated/source/kapt/main")
		sourceDirs.add(kaptMain)
		generatedSourceDirs.add(kaptMain)
	}
}