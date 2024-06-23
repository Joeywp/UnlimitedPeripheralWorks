package site.siredvin.peripheralworks.subsystem.recipe

import net.minecraft.core.RegistryAccess
import net.minecraft.world.Container
import net.minecraft.world.item.crafting.Recipe
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

abstract class RecipeTransformer<V : Container, T : Recipe<V>> {
    abstract fun getInputs(recipe: T, registryAccess: RegistryAccess): List<*>

    abstract fun getOutputs(recipe: T, registryAccess: RegistryAccess): List<*>

    fun getExtraData(@Suppress("UNUSED_PARAMETER") recipe: T): MutableMap<String, Any>? {
        return null
    }

    protected fun serializeIngredients(originalData: List<*>): List<Any> {
        return originalData.stream().filter(Objects::nonNull).map(RecipeRegistryToolkit::serialize).filter {
            it !== RecipeRegistryToolkit.SERIALIZATION_NULL
        }.collect(Collectors.toList<Any>())
    }

    fun transform(recipe: T, registryAccess: RegistryAccess): Map<String, Any> {
        val recipeData: MutableMap<String, Any> = HashMap()
        recipeData["id"] = recipe.id.toString()
        recipeData["type"] = recipe.type.toString()
        recipeData["output"] = serializeIngredients(getOutputs(recipe, registryAccess))
        recipeData["input"] = serializeIngredients(getInputs(recipe, registryAccess))
        val extraData = getExtraData(recipe)
        if (extraData != null) {
            // extra cleanup
            val cleanupList: MutableList<String> = ArrayList()
            for (key in extraData.keys) {
                if (extraData[key] === RecipeRegistryToolkit.SERIALIZATION_NULL) cleanupList.add(key)
            }
            cleanupList.forEach(Consumer { o: String -> extraData.remove(o) })
            recipeData["extra"] = extraData
        }
        return recipeData
    }
}
