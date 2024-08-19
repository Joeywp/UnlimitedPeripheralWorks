@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("site.siredvin.fabric")
    id("site.siredvin.publishing")
    id("site.siredvin.mod-publishing")
}

val modVersion: String by extra
val minecraftVersion: String by extra
val modBaseName: String by extra

baseShaking {
    projectPart.set("fabric")
    integrationRepositories.set(true)
    shake()
}

fabricShaking {
    commonProjectName.set("core")
    createRefmap.set(true)
    accessWidener.set(project(":core").file("src/main/resources/peripheralworks.accesswidener"))
    extraVersionMappings.set(
        mapOf(
            "computercraft" to "cc-tweaked",
            "peripheralium" to "peripheralium",
        ),
    )
    shake()
}

repositories {
    mavenLocal()
    // location of the maven that hosts JEI files since January 2023
    maven {
        name = "Jared's maven"
        url = uri("https://maven.blamejared.com/")
        content {
            includeGroup("mezz.jei")
        }
    }
    maven {
        name = "OwO maven"
        url = uri("https://maven.wispforest.io")
        content {
            includeGroup("io.wispforest")
        }
    }
    maven {
        name = "Polymer repo"
        url = uri("https://maven.nucleoid.xyz")
        content {
            includeGroup("eu.pb4")
            includeGroup("xyz.nucleoid")
        }
    }
    maven {
        name = "OSS Sonatype Repo"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        content {
            includeGroup("me.lucko")
        }
    }
    maven {
        name = "ModMenu maven"
        url = uri("https://maven.terraformersmc.com/releases")
        content {
            includeGroup("com.terraformersmc")
        }
    }
    // for reach entity attributes, required by Magna
    maven {
        url = uri("https://maven.jamieswhiteshirt.com/libs-release/")
        content {
            includeGroup("com.jamieswhiteshirt")
        }
    }
    maven {
        url = uri("https://maven.draylar.dev/releases")
        content {
            includeGroup("dev.draylar")
        }
    }
    maven {
        name = "Jitpack for MI"
        url = uri("https://jitpack.io")
        content {
            /* For Magna */
            includeGroup("com.github.Draylar.omega-config")
        }
    }
    maven {
        name = "Ladysnake Mods"
        url = uri("https://maven.ladysnake.org/releases")
        content {
            includeGroup("io.github.ladysnake")
            includeGroupByRegex("io\\.github\\.onyxstudios.*")
        }
    }
    maven {
        name = "devOS"
        url = uri("https://mvn.devos.one/snapshots/")
        content {
            includeGroup("com.simibubi.create")
            includeGroupByRegex("io\\.github\\.fabricators_of_create.*")
        }
    }
}

dependencies {
    modApi(libs.bundles.externalMods.fabric.integrations.api) {
        exclude("net.fabricmc.fabric-api")
    }

    modImplementation(libs.bundles.fabric.core)
    modImplementation(libs.bundles.fabric)
    modImplementation(libs.bundles.ccfabric) {
        exclude("net.fabricmc.fabric-api")
        exclude("net.fabricmc", "fabric-loader")
        exclude("mezz.jei")
    }

    modRuntimeOnly(libs.bundles.externalMods.fabric.runtime) {
        exclude("net.fabricmc.fabric-api")
        exclude("net.fabricmc", "fabric-loader")
    }

    libs.bundles.externalMods.fabric.integrations.full.get().map { modCompileOnly(it) }
    libs.bundles.externalMods.fabric.integrations.active.get().map { modRuntimeOnly(it) }
    libs.bundles.externalMods.fabric.integrations.activedep.get().map { modRuntimeOnly(it) }
}

publishingShaking {
    shake()
}

modPublishing {
    output.set(tasks.remapJar)
    requiredDependencies.set(
        listOf(
            "cc-tweaked",
            "fabric-language-kotlin",
            "peripheralium",
        ),
    )
    requiredDependenciesCurseforge.add("forge-config-api-port-fabric")
    requiredDependenciesModrinth.add("forge-config-api-port")
    shake()
}
