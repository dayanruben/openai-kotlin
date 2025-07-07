plugins {
    alias(libs.plugins.maven.publish)
    id("java-platform")
}

dependencies {
    constraints {
        api(projects.openaiCore)
        api(projects.openaiClient)
        api(libs.ktor.client.apache)
        api(libs.ktor.client.cio)
        api(libs.ktor.client.java)
        api(libs.ktor.client.jetty)
        api(libs.ktor.client.okhttp)
    }
}
