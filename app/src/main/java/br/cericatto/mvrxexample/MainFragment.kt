package br.cericatto.mvrxexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.main_fragment.*

data class Food(val name: String, val isVegetarian: Boolean, val isGlutenFree: Boolean)

data class HelloWorldState(
    val title: String = "Hello World",
    val foods: List<Food> = listOf(
        Food("Pepperoni Pizza", false, false),
        Food("Cauliflower Pizza", true, true),
        Food("Philly Cheese Pizza", false, false),
        Food("Salmon", false, true)
    )
){
    val excitedTitle = "$title!"
    val vegetarianFoods = foods.filter { it.isVegetarian }
    val glutenFreeFoods = foods.filter { it.isGlutenFree }
}

class MainFragment : Fragment() {

    private val state : HelloWorldState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleView.text = state.excitedTitle
    }
}