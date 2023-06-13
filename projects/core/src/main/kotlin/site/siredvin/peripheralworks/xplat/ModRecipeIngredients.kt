package site.siredvin.peripheralworks.xplat

import net.minecraft.world.item.crafting.Ingredient

interface ModRecipeIngredients {

    companion object {
        private var _IMPL: ModRecipeIngredients? = null

        fun configure(impl: ModRecipeIngredients) {
            _IMPL = impl
        }

        fun get(): ModRecipeIngredients {
            if (_IMPL == null) {
                throw IllegalStateException("You should init PeripheralWorks Platform first")
            }
            return _IMPL!!
        }
    }

    val enderModem: Ingredient
    val peripheralium: Ingredient
    val peripheraliumUpgrade: Ingredient
    val netheriteIngot: Ingredient
    val emerald: Ingredient
    val diamond: Ingredient
    val ironIngot: Ingredient
    val anyCoal: Ingredient
    val peripheraliumBlock: Ingredient
    val observer: Ingredient
    val smoothStone: Ingredient
    val smoothBasalt: Ingredient
    val smoothStoneSlab: Ingredient
    val compass: Ingredient
    val stick: Ingredient
}
