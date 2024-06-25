package site.siredvin.peripheralworks.subsystem.recipe

import net.minecraft.core.RegistryAccess
import net.minecraft.world.Container
import net.minecraft.world.item.crafting.Recipe

object DefaultRecipeTransformer : RecipeTransformer<Container, Recipe<Container>>() {
    override fun getInputs(recipe: Recipe<Container>, registryAccess: RegistryAccess): List<*> {
        return recipe.ingredients
    }

    override fun getOutputs(recipe: Recipe<Container>, registryAccess: RegistryAccess): List<*> {
        return listOf(recipe.getResultItem(registryAccess))
    }
}
