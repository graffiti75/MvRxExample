package br.cericatto.mvrxexample

import android.app.Application
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CasterModule {
    @Singleton
    @Provides
    fun provideUserRepository() = UserRepository()
}

@Singleton
@Component(modules = [CasterModule::class])
interface CasterComponent {
    fun userRepository(): UserRepository
}

class CasterApplication : Application() {
    val component = DaggerCasterComponent.create()
}