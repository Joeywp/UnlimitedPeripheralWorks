package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity
import site.siredvin.peripheralium.api.peripheral.IPeripheralPlugin

abstract class CreateSmartBlockPeripheralPlugin<T : SmartBlockEntity>(
    protected val blockEntity: T,
) : IPeripheralPlugin
