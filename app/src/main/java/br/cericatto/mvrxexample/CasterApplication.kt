package br.cericatto.mvrxexample

import android.app.Application

class CasterApplication : Application() {
    val userRepository = UserRepository()
}