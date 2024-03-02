package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.contraptions.elevator.ElevatorPulleyBlockEntity
import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.lua.MethodResult
import site.siredvin.peripheralium.api.peripheral.IPeripheralPlugin

class CreateElevatorPulley(private val elevatorPulleyBlockEntity: ElevatorPulleyBlockEntity) : IPeripheralPlugin {
    @LuaFunction(mainThread = true)
    fun simulateClick() {
        elevatorPulleyBlockEntity.clicked()
    }

    @LuaFunction(mainThread = true)
    fun isRunning(): MethodResult {
        return MethodResult.of(elevatorPulleyBlockEntity.running)
    }
}