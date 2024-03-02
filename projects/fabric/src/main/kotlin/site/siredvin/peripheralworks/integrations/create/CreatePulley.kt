package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.contraptions.pulley.PulleyBlockEntity
import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.lua.MethodResult

open class CreatePulley<T : PulleyBlockEntity>(blockEntity: T) :
    CreateSmartBlock<T>(blockEntity) {

    @LuaFunction(mainThread = true)
    fun isRunning(): MethodResult {
        return MethodResult.of(blockEntity.running)
    }

    @LuaFunction(mainThread = true)
    fun getMovementSpeed(): MethodResult {
        return MethodResult.of(blockEntity.movementSpeed)
    }
}