package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.contraptions.elevator.ElevatorPulleyBlockEntity
import com.simibubi.create.content.contraptions.pulley.PulleyBlockEntity
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.Level
import site.siredvin.peripheralium.api.peripheral.IPeripheralPlugin
import site.siredvin.peripheralworks.api.PeripheralPluginProvider
import site.siredvin.peripheralworks.common.configuration.PeripheralWorksConfig
import site.siredvin.peripheralworks.computercraft.ComputerCraftProxy

class Integration : Runnable {

    object CreatePluginProvider : PeripheralPluginProvider {
        override val pluginType: String
            get() = "create"

        override fun provide(level: Level, pos: BlockPos, side: Direction): IPeripheralPlugin? {
            val blockEntity = level.getBlockEntity(pos)
            if (!Configuration.enableCreate || blockEntity == null) return null

            if (blockEntity is BlazeBurnerBlockEntity) {
                return CreateBlazeBurner(blockEntity)
            } else if (blockEntity is ElevatorPulleyBlockEntity) {
                return CreateElevatorPulley(blockEntity)
            } else if (blockEntity is PulleyBlockEntity) {
                return CreatePulley(blockEntity)
            } else if (blockEntity is SmartBlockEntity) {
                val filterBehavior = blockEntity.getBehaviour(FilteringBehaviour.TYPE)
                if (filterBehavior != null) {
                    return CreateFilterableBehavior(blockEntity, filterBehavior)
                }
            }
            return null
        }

    }

    override fun run() {
        ComputerCraftProxy.addProvider(CreatePluginProvider)
        PeripheralWorksConfig.registerIntegrationConfiguration(Configuration)
    }
}
