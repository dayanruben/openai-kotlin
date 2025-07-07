plugins {
    id("org.jetbrains.kotlin.jvm")
    alias(libs.plugins.kotlinx.serialization)
    application
}

dependencies {
    //implementation("com.aallam.openai:openai-client:<version>")
    implementation(projects.openaiClient)
    implementation(libs.ktor.client.apache)
    implementation(libs.ktoken)
}

application {
    mainClass.set("com.aallam.openai.sample.jvm.AppKt")
}
