package br.cericatto.mvrxexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import io.reactivex.Observable
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.concurrent.TimeUnit

data class HelloWorldState(
    val title: String = "Hello World!",
    val temperature: Async<Int> = Uninitialized
) : MvRxState

class HelloWorldViewModel(initialState: HelloWorldState) : MvRxViewModel<HelloWorldState>(initialState) {
    fun fetchTemperature() {
        Observable.just(72)
            .delay(3, TimeUnit.SECONDS)
            .execute { copy(temperature = it) }
    }
}

class MainFragment : BaseMvRxFragment() {

    // fragmentViewModel wraps Android's Jetpack ViewModelProvider, which will either create
    // a new instance or give you an existing one if there is anyone in scope. And it will scope it
    // to the current fragment. It will also subscribe ViewModel in a lifecycle aware way, which will
    // automatically call invalidate() for any state changes that occur when the fragment started.
    private val viewModel : HelloWorldViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleView.setOnClickListener {
            viewModel.fetchTemperature()
        }
    }

    // invalidate will be automatically get called any time the state changes for our ViewModel's.
    override fun invalidate() = withState(viewModel) { state ->
        titleView.text = when (state.temperature) {
            is Uninitialized -> "Click to load weather"
            is Loading -> "Loading"
            is Success -> "Weather: ${state.temperature()} degrees"
            is Fail -> "Failed to load weather"
        }
    }
}