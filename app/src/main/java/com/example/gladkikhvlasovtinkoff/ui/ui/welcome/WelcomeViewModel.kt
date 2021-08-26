package com.example.gladkikhvlasovtinkoff.ui.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
@Inject constructor(val authRepository: AuthRepository) : ViewModel() {
    private val _viewState: MutableLiveData<AuthViewState> = MutableLiveData()
    val viewState: LiveData<AuthViewState>
        get() = _viewState


    fun logInWithAccount(account: GoogleSignInAccount) {
        val disposable = authRepository.logInWithAccount(
            account
        )
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { viewState ->
                    _viewState.postValue(viewState)
                },
                {
                    it.printStackTrace()
                }
            )
    }
}