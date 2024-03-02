package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity
import com.simibubi.create.content.processing.burner.BlazeBurnerBlockEntity.FuelType.*
import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.lua.MethodResult

class CreateBlazeBurner(blockEntity: BlazeBurnerBlockEntity) : CreateSmartBlock<BlazeBurnerBlockEntity>(blockEntity) {
    @LuaFunction(mainThread = true)
    fun getFuelType(): MethodResult {
        return MethodResult.of(
            when (blockEntity.activeFuel) {
                NONE -> "none"
                NORMAL -> "normal"
                SPECIAL -> "special"
                null -> null
            }
        )
    }

    @LuaFunction(mainThread = true)
    fun getRemainingBurnTime(): MethodResult {
        return MethodResult.of(blockEntity.remainingBurnTime)
    }
}