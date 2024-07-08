package site.siredvin.peripheralworks.integrations.alloy_forgery

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableAlloyForgeryConfig: ForgeConfigSpec.BooleanValue? = null

    val enableAlloyForgery: Boolean
        get() = enableAlloyForgeryConfig?.get() ?: true

    override val name: String
        get() = "alloy_forgery"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableAlloyForgeryConfig = builder.comment("Enables alloy forgery integration")
            .define("enableAlloyForgery", true)
    }
}
