package br.cericatto.mvrxexample

import com.airbnb.mvrx.MvRxState
import org.junit.Assert.*
import org.junit.Test

data class Food(val name: String, val isVegetarian: Boolean, val isGlutenFree: Boolean)

data class FoodState(val foods: Set<Food> = emptySet()) : MvRxState {
    val vegetarianFoods = foods.filter(Food::isVegetarian).toSet()
    val glutenFreeFoods = foods.filter(Food::isGlutenFree).toSet()

    fun containsFood(foodName: String) = foods.any { it.name.equals(foodName, ignoreCase = true) }
}

class FoodStateTest {
    @Test
    fun testNoFoods() {
        val state = FoodState()
        assertEquals(emptySet<Food>(), state.vegetarianFoods)
        assertEquals(emptySet<Food>(), state.glutenFreeFoods)
    }

    @Test
    fun testFoods() {
        val pepperoniPizza = Food("Pepperoni Pizza", isVegetarian = false, isGlutenFree = false)
        val cauliFlowerPizza = Food("Cauliflower Pizza", isVegetarian = true, isGlutenFree = true)
        val phillyCheeseSteak = Food("Philly Cheese Steak", isVegetarian = false, isGlutenFree = false)
        val salmon = Food("Salmon", isVegetarian = false, isGlutenFree = true)
        val state = FoodState(foods = setOf(pepperoniPizza, cauliFlowerPizza, phillyCheeseSteak, salmon))
        assertEquals(setOf(cauliFlowerPizza), state.vegetarianFoods)
        assertEquals(setOf(cauliFlowerPizza, salmon), state.glutenFreeFoods)
    }

    @Test
    fun testContainsFood() {
        val salmon = Food("Salmon", isVegetarian = false, isGlutenFree = true)
        val state = FoodState(foods = setOf(salmon))
        assertTrue(state.containsFood("salmon"))
        assertFalse(state.containsFood("pepperoni pizza"))
    }
}