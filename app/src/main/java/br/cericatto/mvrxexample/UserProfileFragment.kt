package br.cericatto.mvrxexample

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.*
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.main_fragment.*

data class UserProfileState(val userId: String) : MvRxState {
    constructor(args: UserProfileArgs) : this(args.userId)
}

class UserProfileViewModel(initialState: UserProfileState) : MvRxViewModel<UserProfileState>(initialState)

@Parcelize
data class UserProfileArgs(val userId: String) : Parcelable

class UserProfileFragment : BaseMvRxFragment() {

    private val viewModel : UserProfileViewModel by fragmentViewModel()
//    private val args: UserProfileArgs by args()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun invalidate() = withState(viewModel) { state ->
        titleView.text = state.userId
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