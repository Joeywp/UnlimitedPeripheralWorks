package site.siredvin.peripheralworks.integrations.deepresonance

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private var enableResonatingCrystalConfig: ForgeConfigSpec.BooleanValue? = null
    private var enableGeneratorPartConfig: ForgeConfigSpec.BooleanValue? = null
    private var enableTankConfig: ForgeConfigSpec.BooleanValue? = null

    val enableResonatingCrystal: Boolean
        get() = enableResonatingCrystalConfig?.get() ?: true

    val enableGeneratorPart: Boolean
        get() = enableGeneratorPartConfig?.get() ?: true

    val enableTank: Boolean
        get() = enableTankConfig?.get() ?: true

    override val name: String
        get() = "deep_resonance"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableResonatingCrystalConfig = builder.comment("Enables resonating crystal integration").define("enableResonatingCrystal", true)
        enableGeneratorPartConfig = builder.comment("Enables generator part integration").define("enableGeneratorPart", true)
        enableTankConfig = builder.comment("Enables tank").define("enableTank", true)
    }
}
