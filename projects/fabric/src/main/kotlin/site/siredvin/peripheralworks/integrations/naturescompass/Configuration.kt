package site.siredvin.peripheralworks.integrations.naturescompass

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableTurtleUpgradeConfig: ForgeConfigSpec.BooleanValue? = null
    private var enablePocketUpgradeConfig: ForgeConfigSpec.BooleanValue? = null

    val enableNaturesCompassTurtleUpgrade: Boolean
        get() = enableTurtleUpgradeConfig?.get() ?: true

    val enableNaturesCompassPocketUpgrade: Boolean
        get() = enablePocketUpgradeConfig?.get() ?: true

    override val name: String
        get() = "naturescompass"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableTurtleUpgradeConfig = builder.comment("Enables usage of natures compass as turtle upgrade")
            .define("enableNaturesCompassTurtleUpgrade", true)
        enablePocketUpgradeConfig = builder.comment("Enables usage of natures compass as pocket upgrade")
            .define("enableNaturesCompassPocketUpgrade", true)
    }
}
