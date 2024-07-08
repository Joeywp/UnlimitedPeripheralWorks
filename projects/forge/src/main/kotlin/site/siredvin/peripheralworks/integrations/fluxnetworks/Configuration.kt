package site.siredvin.peripheralworks.integrations.fluxnetworks

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableFluxControllerConfig: ForgeConfigSpec.BooleanValue? = null

    val enableFluxController: Boolean
        get() = enableFluxControllerConfig?.get() ?: true

    override val name: String
        get() = "flux_networks"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableFluxControllerConfig = builder.comment("Enables flux controller integration")
            .define("enableAutomobile", true)
    }
}
