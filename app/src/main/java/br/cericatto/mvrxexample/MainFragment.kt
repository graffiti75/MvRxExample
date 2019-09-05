package br.cericatto.mvrxexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.cericatto.mvrxexample.R
import kotlinx.android.synthetic.main.main_fragment.*

data class HelloWorldState(val title : String = "Hello World!")

class MainFragment : Fragment() {
    private val state = HelloWorldState()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleView.text = state.title
    }
}