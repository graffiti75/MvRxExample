package br.cericatto.mvrxexample

import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity

class MainActivity : BaseMvRxActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, MainFragment())
                .replace(R.id.container2, MainFragment())
                .commit()
        }
    }
}