package br.cericatto.mvrxexample

import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.test.MvRxTestRule
import com.airbnb.mvrx.withState
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test

data class SimpleFood(val name: String, val isVegetarian: Boolean, val isGlutenFree: Boolean)

data class SimpleFoodState(val foods: Set<SimpleFood> = emptySet()) : MvRxState

class SimpleFoodViewModel(initialState: SimpleFoodState) : MvRxViewModel<SimpleFoodState>(initialState) {
    fun addFood(food: SimpleFood) = setState { copy(foods = foods + food) }
}

class FoodViewModelTest {

    companion object {
        @JvmField
        @ClassRule
        val mvrxRule = MvRxTestRule()
    }

    @Test
    fun testInitialState() {
        val salmon = SimpleFood("Salmon", isVegetarian = false, isGlutenFree = true)
        val initialState = SimpleFoodState(setOf(salmon))
        val viewModel = SimpleFoodViewModel(initialState)
        withState(viewModel) { state ->
            assertEquals(initialState, state)
        }
    }

    @Test
    fun testAddFood() {
        val salmon = SimpleFood("Salmon", isVegetarian = false, isGlutenFree = true)
        val initialState = SimpleFoodState(setOf(salmon))
        val viewModel = SimpleFoodViewModel(initialState)
        val pepperoniPizza = SimpleFood("Pepperoni Pizza", isVegetarian = false, isGlutenFree = false)
        viewModel.addFood(pepperoniPizza)
        withState(viewModel) { state ->
            assertEquals(setOf(salmon, pepperoniPizza), state.foods)
        }
    }
}