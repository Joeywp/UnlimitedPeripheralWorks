@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("site.siredvin.root") version "0.3.12"
}

subprojectShaking {
    withKotlin.set(true)
}

val setupSubproject = subprojectShaking::setupSubproject


subprojects {
    setupSubproject(this)
}

repositories {
    mavenCentral()
}