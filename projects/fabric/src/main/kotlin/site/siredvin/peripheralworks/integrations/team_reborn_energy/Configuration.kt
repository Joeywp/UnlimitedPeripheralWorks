package site.siredvin.peripheralworks.integrations.team_reborn_energy

import net.minecraftforge.common.ForgeConfigSpec
import site.siredvin.peripheralium.api.config.IConfigHandler

object Configuration : IConfigHandler {

    private const val DEFAULT_ENERGY_TO_FUEL_RATE = 50 // really calculated :) Trust me

    private var enableEnergyStorageConfig: ForgeConfigSpec.BooleanValue? = null
    private var enableTurtleRefuelWithEnergyConfig: ForgeConfigSpec.BooleanValue? = null
    private var energyToFuelRateConfig: ForgeConfigSpec.IntValue? = null

    val enableEnergyStorage: Boolean
        get() = enableEnergyStorageConfig?.get() ?: true
    val enableTurtleRefuelWithEnergy: Boolean
        get() = enableTurtleRefuelWithEnergyConfig?.get() ?: true
    val energyToFuelRate: Int
        get() = energyToFuelRateConfig?.get() ?: DEFAULT_ENERGY_TO_FUEL_RATE

    override val name: String
        get() = "team_reborn_energy"

    override fun addToConfig(builder: ForgeConfigSpec.Builder) {
        enableEnergyStorageConfig = builder.comment("Enables energy storage integration").define("enableEnergyStorage", true)
        enableTurtleRefuelWithEnergyConfig = builder.comment("Enables turtle refueling with items with energy")
            .define("enableTurtleRefuelWithEnergy", true)
        energyToFuelRateConfig = builder.comment("Controls how many energy required for one fuel point")
            .defineInRange("energyToFuelRate", DEFAULT_ENERGY_TO_FUEL_RATE, 1, Int.MAX_VALUE)
    }
}
