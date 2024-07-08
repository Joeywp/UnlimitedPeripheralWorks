@file:Suppress("ktlint:standard:package-name")

package site.siredvin.peripheralworks.integrations.toms_storage

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableTomsStorageConfig: ForgeConfigSpec.BooleanValue? = null

    val enableTomsStorage: Boolean
        get() = enableTomsStorageConfig?.get() ?: true

    override val name: String
        get() = "toms_storage"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableTomsStorageConfig = builder.comment("Enables toms storage integration, even if you disable this, generic item storage integration will work time-to-time")
            .define("enableTomsStorage", true)
    }
}
