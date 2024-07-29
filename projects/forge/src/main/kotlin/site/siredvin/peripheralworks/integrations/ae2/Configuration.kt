package site.siredvin.peripheralworks.integrations.ae2

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableMEInterfaceConfig: ForgeConfigSpec.BooleanValue? = null
    private var enableStorageIntegrationConfig: ForgeConfigSpec.BooleanValue? = null

    val enableMEInterface: Boolean
        get() = enableMEInterfaceConfig?.get() ?: true

    val enableStorageIntegrations: Boolean
        get() = enableStorageIntegrationConfig?.get() ?: true

    override val name: String
        get() = "ae2"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableMEInterfaceConfig = builder.comment("Enables me blocks integration").define("enableMEInterface", true)
        enableMEInterfaceConfig = builder.comment("Enables me integration with storages").define("enableStorageIntegrations", true)
    }
}
