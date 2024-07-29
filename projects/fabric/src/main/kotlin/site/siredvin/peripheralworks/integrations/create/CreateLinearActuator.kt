package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.contraptions.piston.LinearActuatorBlockEntity
import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.lua.MethodResult

class CreateLinearActuator(
    blockEntity: LinearActuatorBlockEntity,
) : CreateSmartBlock<LinearActuatorBlockEntity>(blockEntity) {
    @LuaFunction(mainThread = true)
    fun isRunning(): MethodResult {
        return MethodResult.of(blockEntity.running)
    }

    @LuaFunction(mainThread = true)
    fun getMovementSpeed(): MethodResult {
        return MethodResult.of(blockEntity.movementSpeed)
    }
}