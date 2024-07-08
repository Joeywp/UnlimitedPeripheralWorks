package site.siredvin.peripheralworks.integrations.modern_industrialization

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableEnergyStorageConfig: ForgeConfigSpec.BooleanValue? = null
    private var enableCraftingMachineConfig: ForgeConfigSpec.BooleanValue? = null

    val enableEnergyStorage: Boolean
        get() = enableEnergyStorageConfig?.get() ?: true
    val enableCraftingMachine: Boolean
        get() = enableCraftingMachineConfig?.get() ?: true

    override val name: String
        get() = "modern_industrialization"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableEnergyStorageConfig = builder.comment("Enables energy storage integration").define("enableEnergyStorage", true)
        enableCraftingMachineConfig = builder.comment("Enables crafting machine integration").define("enableCraftingMachine", true)
    }
}
