package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity.FuelType.*
import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.lua.MethodResult
import site.siredvin.peripheralium.api.peripheral.IPeripheralPlugin

class CreateBlazeBurner(private val blazeBurnerBlockEntity: BlazeBurnerBlockEntity) : IPeripheralPlugin {
    @LuaFunction(mainThread = true)
    fun getFuelType(): MethodResult {
        return MethodResult.of(
            when (blazeBurnerBlockEntity.activeFuel) {
                NONE -> "none"
                NORMAL -> "normal"
                SPECIAL -> "special"
                null -> null
            }
        )
    }

    @LuaFunction(mainThread = true)
    fun getRemainingBurnTime(): MethodResult {
        return MethodResult.of(blazeBurnerBlockEntity.remainingBurnTime)
    }
}