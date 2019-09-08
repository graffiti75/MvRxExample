package br.cericatto.mvrxexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.BaseMvRxFragment
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.main_fragment.*
import kotlin.random.Random

data class HelloWorldState(val title : String = "Hello World ${Random.nextInt(100)}") : MvRxState {
    val excitedTitle = "$title!"
}

class HelloWorldViewModel(initialState: HelloWorldState) : MvRxViewModel<HelloWorldState>(initialState)

class MainFragment : BaseMvRxFragment() {

    // fragmentViewModel wraps Android's Jetpack ViewModelProvider, which will either create
    // a new instance or give you an existing one if there is anyone in scope. And it will scope it
    // to the current fragment. It will also subscribe ViewModel in a lifecycle aware way, which will
    // automatically call invalidate() for any state changes that occur when the fragment started.
    private val viewModel : HelloWorldViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    // invalidate will be automatically get called any time the state changes for our ViewModel's.
    override fun invalidate() = withState(viewModel) { state ->
        titleView.text = state.excitedTitle
    }
}