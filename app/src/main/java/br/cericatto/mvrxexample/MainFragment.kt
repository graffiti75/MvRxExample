package br.cericatto.mvrxexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.main_fragment.*

data class HelloWorldState(val title: String = "Hello World!") : MvRxState {
    val excitedTitle = "$title!"
}

class HelloWorldViewModel(initialState: HelloWorldState) : MvRxViewModel<HelloWorldState>(initialState)

class MainFragment : Fragment() {
    private val viewModel = HelloWorldViewModel(HelloWorldState())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = withState(viewModel) { state ->
        titleView.text = state.excitedTitle
    }
}