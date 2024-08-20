package site.siredvin.peripheralworks.integrations.integrateddynamics

import com.google.common.collect.MapMaker
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag

object IntegratedDynamicProxy {
    val outputData: MutableMap<Int, CompoundTag> = MapMaker().concurrencyLevel(4).makeMap()
    val inputData: MutableMap<Int, Tag> = MapMaker().concurrencyLevel(4).makeMap()
}
