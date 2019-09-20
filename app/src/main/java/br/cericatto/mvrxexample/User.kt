package br.cericatto.mvrxexample

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

data class User(val userId: String, val name: String)

class UserRepository {
    fun getUser(userId: String) = Observable.just(User(userId, "Gabriel Peal")).delay(2, TimeUnit.SECONDS)
}