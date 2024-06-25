package site.siredvin.peripheralworks.subsystem.recipe

import net.minecraft.core.RegistryAccess
import net.minecraft.world.Container
import net.minecraft.world.item.crafting.Recipe
import java.util.function.Consumer
import java.util.stream.Collectors
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

abstract class RecipeTransformer<V : Container, T : Recipe<V>> {
    open fun getInputs(recipe: T, registryAccess: RegistryAccess): List<*> {
        return recipe.ingredients
    }

    open fun getOutputs(recipe: T, registryAccess: RegistryAccess): List<*> {
        return listOf(recipe.getResultItem(registryAccess))
    }

    open fun getExtraData(@Suppress("UNUSED_PARAMETER") recipe: T): MutableMap<String, Any>? {
        return null
    }

    protected fun serializeIngredients(originalData: List<*>): List<Any> {
        return originalData.stream().map {
            // null in the ingredient lists may be important!
            //
            // For example, shaped minecraft crafting recipes contain null's
            // to indicate an empty slot. In order to retain the recipe shape,
            // the null's need to be preserved!
            it ?: RecipeRegistryToolkit.SERIALIZATION_EMPTY_SLOT
        }.map(RecipeRegistryToolkit::serialize).filter {
            it !== RecipeRegistryToolkit.SERIALIZATION_SKIP
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
                if (extraData[key] === RecipeRegistryToolkit.SERIALIZATION_SKIP) cleanupList.add(key)
            }
            cleanupList.forEach(Consumer { o: String -> extraData.remove(o) })
            recipeData["extra"] = extraData
        }
        return recipeData
    }
}
