package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.processing.basin.BasinBlockEntity
import io.github.foundationgames.automobility.entity.AutomobileEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.entity.Entity
import net.minecraft.world.level.Level
import site.siredvin.peripheralium.api.peripheral.IPeripheralPlugin
import site.siredvin.peripheralworks.api.PeripheralPluginProvider
import site.siredvin.peripheralworks.common.configuration.PeripheralWorksConfig
import site.siredvin.peripheralworks.computercraft.ComputerCraftProxy
import site.siredvin.peripheralworks.integrations.alloy_forgery.ForgeControllerPlugin
import site.siredvin.peripheralworks.integrations.alloy_forgery.Integration
import site.siredvin.peripheralworks.subsystem.entityperipheral.EntityPeripheralLookup
import site.siredvin.peripheralworks.subsystem.entityperipheral.EntityPeripheralPluginProvider
import wraith.alloyforgery.block.ForgeControllerBlockEntity

class Integration : Runnable {

    object CreatePluginProvider : PeripheralPluginProvider {
        override val pluginType: String
            get() = "create"

        override fun provide(level: Level, pos: BlockPos, side: Direction): IPeripheralPlugin? {
            val blockEntity = level.getBlockEntity(pos)
            if (Configuration.enableCreate && blockEntity is BasinBlockEntity) {
                return CreateBasin(blockEntity)
            }
            return null
        }

    }
    override fun run() {
        ComputerCraftProxy.addProvider(CreatePluginProvider)
        PeripheralWorksConfig.registerIntegrationConfiguration(Configuration)
    }
}
