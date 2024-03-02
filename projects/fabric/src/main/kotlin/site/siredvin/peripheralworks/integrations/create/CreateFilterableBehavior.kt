package site.siredvin.peripheralworks.integrations.create

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity
import com.simibubi.create.foundation.blockEntity.behaviour.filtering.FilteringBehaviour
import dan200.computercraft.api.lua.LuaException
import dan200.computercraft.api.lua.LuaFunction
import dan200.computercraft.api.lua.MethodResult
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import site.siredvin.peripheralium.api.peripheral.IPeripheralPlugin
import site.siredvin.peripheralium.xplat.XplatRegistries

class CreateFilterableBehavior(
    private val basinBlockEntity: SmartBlockEntity,
    private val filterableBehavior: FilteringBehaviour,
) : IPeripheralPlugin {
    @LuaFunction(mainThread = true)
    fun getFilterName(): MethodResult {
        return MethodResult.of(filterableBehavior.filter?.item?.let {
            XplatRegistries.ITEMS.getKey(it).toString()
        })
    }

    @LuaFunction(mainThread = true)
    fun setFilterItem(itemId: String): MethodResult {
        val item = try {
            XplatRegistries.ITEMS.get(ResourceLocation(itemId))
        } catch (e: Exception) {
            throw LuaException(e.message)
        }

        if (item != null) {
            return MethodResult.of(filterableBehavior.setFilter(ItemStack(item, 1)))
        }

        return MethodResult.of(false)
    }

    @LuaFunction(mainThread = true)
    fun clearFilterItem(): MethodResult {
        return setFilterItem("minecraft:air")
    }
}