package br.cericatto.mvrxexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import kotlinx.android.synthetic.main.main_fragment.*

data class UserProfileState(val userId: String) : MvRxState

class UserProfileViewModel(initialState: UserProfileState) : MvRxViewModel<UserProfileState>(initialState) {
    companion object : MvRxViewModelFactory<UserProfileViewModel, UserProfileState> {
        override fun initialState(viewModelContext: ViewModelContext) : UserProfileState? {
            val userId = (viewModelContext as FragmentViewModelContext).fragment<UserProfileFragment>().getUserId()
            return UserProfileState(userId)
        }
    }
}

class UserProfileFragment : BaseMvRxFragment() {

    private val viewModel : UserProfileViewModel by fragmentViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    fun getUserId() = arguments?.getString(ARG_USER_ID) ?: throw IllegalArgumentException("You must provide a user id.")

    override fun invalidate() = withState(viewModel) { state ->
        titleView.text = state.userId
    }

    companion object {
        private const val ARG_USER_ID = "user_id"
        fun newInstance(userId: String): UserProfileFragment {
            val fragment = UserProfileFragment()
            val args = Bundle()
            args.putString(ARG_USER_ID, userId)
            fragment.arguments = args
            return fragment
        }
    }
}