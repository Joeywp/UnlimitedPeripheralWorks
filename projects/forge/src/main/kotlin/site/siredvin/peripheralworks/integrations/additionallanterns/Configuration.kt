package site.siredvin.peripheralworks.integrations.additionallanterns

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableLanternsConfig: ForgeConfigSpec.BooleanValue? = null

    val enableLanterns: Boolean
        get() = enableLanternsConfig?.get() ?: true

    override val name: String
        get() = "additionallanterns"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableLanternsConfig = builder.comment("Enables lanterns integration").define("enableLanterns", true)
    }
}
