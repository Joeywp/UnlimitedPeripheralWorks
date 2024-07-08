package site.siredvin.peripheralworks.integrations.easy_villagers

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableAutoTradeConfig: ForgeConfigSpec.BooleanValue? = null

    val enableAutoTrader: Boolean
        get() = enableAutoTradeConfig?.get() ?: true

    override val name: String
        get() = "easy_villagers"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableAutoTradeConfig = builder.comment("Enables auto trader integration").define("enableAutoTrader", true)
    }
}
