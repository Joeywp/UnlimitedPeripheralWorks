package site.siredvin.peripheralworks.integrations.ae2

import appeng.api.stacks.*
import dan200.computercraft.api.lua.LuaException
import net.minecraft.resources.ResourceLocation
import site.siredvin.peripheralium.util.representation.LuaRepresentation
import site.siredvin.peripheralium.xplat.PeripheraliumPlatform
import site.siredvin.peripheralium.xplat.XplatRegistries
import java.util.function.Predicate

object AE2Helper {

    private val ALWAYS: Predicate<AEKey> = Predicate { true }

    fun genericStackToMap(stack: GenericStack): MutableMap<String, Any> {
        if (stack.what is AEItemKey) {
            val base = LuaRepresentation.forItemStack((stack.what as AEItemKey).toStack(stack.amount.toInt()))
            base["type"] = "item"
            return base
        }
        val base = mutableMapOf<String, Any>()
        base["type"] = "fluid"
        base["name"] = XplatRegistries.FLUIDS.getKey((stack.what as AEFluidKey).fluid).toString()
        base["count"] = stack.amount.toDouble() / PeripheraliumPlatform.fluidCompactDivider
        return base
    }

    fun keyCounterToLua(counter: KeyCounter, predicate: Predicate<AEKey> = ALWAYS, displayType: Boolean = false): List<Map<String, Any>> {
        return counter
            .mapNotNull { entry ->
                val aeKey = entry.key
                when {
                    !predicate.test(aeKey) -> null
                    aeKey is AEItemKey -> {
                        val data = LuaRepresentation.forItemStack(aeKey.toStack(entry.longValue.toInt()))
                        data.remove("maxStackSize")
                        if (displayType) {
                            data["type"] = "item"
                        }
                        data
                    }
                    aeKey is AEFluidKey -> {
                        val data = mutableMapOf(
                            "name" to XplatRegistries.FLUIDS.getKey(aeKey.fluid).toString(),
                            "amount" to entry.longValue / PeripheraliumPlatform.fluidCompactDivider,
                        )
                        if (displayType) {
                            data["type"] = "fluid"
                        }
                        data
                    }
                    else -> null
                }
            }
    }

    fun buildKey(mode: String, id_key: String): AEKey {
        return when (mode) {
            "fluid" -> {
                val fluid = XplatRegistries.FLUIDS.get(ResourceLocation(id_key))
                AEFluidKey.of(fluid)
            }
            "item" -> {
                val item = XplatRegistries.ITEMS.get(ResourceLocation(id_key))
                AEItemKey.of(item)
            }
            else -> {
                throw LuaException("first argument should be 'fluid' or 'item'")
            }
        }
    }
}
