package site.siredvin.peripheralworks.integrations.integrateddynamics

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableVariableStoreConfig: ForgeConfigSpec.BooleanValue? = null
    private var enableComputerAspectConfig: ForgeConfigSpec.BooleanValue? = null

    val enableVariableStore: Boolean
        get() = enableVariableStoreConfig?.get() ?: true

    val enableComputerAspect: Boolean
        get() = enableComputerAspectConfig?.get() ?: true

    override val name: String
        get() = "integrateddynamics"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableVariableStoreConfig = builder.comment("Enables variable store integration").define("enableVariableStore", true)
        enableComputerAspectConfig = builder.comment("Enables computer aspect for block reader").define("enableComputerAspect", true)
    }
}
