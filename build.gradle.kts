import org.apache.tools.ant.taskdefs.condition.Os

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.72"
    kotlin("kapt") version "1.3.72"
    application
    id("com.palantir.graal") version "0.7.1-8-g98afa4e"
}

repositories {
    jcenter()
}

val mainClassName = "net.mocanu.trials.graal.cli.AppKt"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("info.picocli:picocli:4.3.2")

    kapt("info.picocli:picocli-codegen:4.3.2")
}

application {
    mainClassName = mainClassName
}

graal {
    downloadBaseUrl("https://github.com/graalvm/graalvm-ce-builds/releases/download")
    graalVersion("20.1.0")
    javaVersion("11")
    mainClass(mainClassName)
    outputName("trials-graal-cli")
    option("--no-server")
    option("--no-fallback")

    if (!Os.isFamily(Os.FAMILY_MAC)) {
        // using this under Map produces an error: "DARWIN does not support building static executable images"
        option("--static")
    }
}
