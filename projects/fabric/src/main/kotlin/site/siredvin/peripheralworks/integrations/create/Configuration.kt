package site.siredvin.peripheralworks.integrations.create

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var ENABLE_CREATE_ENTITY: ForgeConfigSpec.BooleanValue? = null

    val enableCreate: Boolean
        get() = ENABLE_CREATE_ENTITY?.get() ?: true

    override val name: String
        get() = "create"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        ENABLE_CREATE_ENTITY = builder.comment("Enables create integration")
            .define("enableCreate", true)
    }
}
