package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.content.contraptions.elevator.ElevatorPulleyBlockEntity
import dan200.computercraft.api.lua.LuaFunction

class CreateElevatorPulley(blockEntity: ElevatorPulleyBlockEntity) :
    CreatePulley<ElevatorPulleyBlockEntity>(blockEntity) {
    @LuaFunction(mainThread = true)
    fun simulateClick() {
        blockEntity.clicked()
    }
}