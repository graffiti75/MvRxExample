package br.cericatto.mvrxexample

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.main_fragment.*

data class UserProfileState(val user: Async<User> = Uninitialized) : MvRxState

class UserProfileViewModel(
    initialState: UserProfileState,
    private val userId: String,
    private val userRepository: UserRepository
) : MvRxViewModel<UserProfileState>(initialState) {

    fun fetchUser() {
        userRepository.getUser(userId).execute { copy(user = it) }
    }

    companion object : MvRxViewModelFactory<UserProfileViewModel, UserProfileState> {
        override fun create(viewModelContext: ViewModelContext, state: UserProfileState): UserProfileViewModel? {
            val userId = viewModelContext.args<UserProfileArgs>().userId
            val userRepository = viewModelContext.app<CasterApplication>().userRepository
            return UserProfileViewModel(state, userId, userRepository)
        }
    }
}

@Parcelize
data class UserProfileArgs(val userId: String) : Parcelable

class UserProfileFragment : BaseMvRxFragment() {

    private val viewModel : UserProfileViewModel by fragmentViewModel()
//    private val args: UserProfileArgs by args()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleView.setOnClickListener {
            viewModel.fetchUser()
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        titleView.text = state.user.toString()
    }

    companion object {
        private const val ARG_USER_ID = "user_id"
        fun newInstance(userId: String): UserProfileFragment {
            val fragment = UserProfileFragment()
            val args = Bundle()
            args.putParcelable(MvRx.KEY_ARG, UserProfileArgs(userId))
            fragment.arguments = args
            return fragment
        }
    }
}