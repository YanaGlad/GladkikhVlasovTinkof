package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.repository.WalletRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


 
@HiltViewModel
class WalletsViewModel @Inject constructor(val repository: WalletRepository): ViewModel() {
    companion object{
        const val TEMP_WALLET_ID = 1L
    }
    private val _viewState : MutableLiveData<WalletListViewState> = MutableLiveData()
    val viewState : LiveData<WalletListViewState>
    get() = _viewState

    fun addWallet(walletData: WalletData){
        repository.addWallet(walletData)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnComplete{
                _viewState.postValue(WalletListViewState.SuccessOperation)
            }
            .doOnError{
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribe()
    }

    fun getWalletList(){
        repository.getWalletsByUserId(TEMP_WALLET_ID)
            .doOnNext { viewState ->
                _viewState.postValue(viewState)
            }
            .doOnError{
                _viewState.postValue(WalletListViewState.Error.UnexpectedError)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
     }
    
        var hiddenWalletList: MutableLiveData<ArrayList<WalletData>> =
        MutableLiveData<ArrayList<WalletData>>()

    init {
         hiddenWalletList.value = ArrayList()


        hiddenWalletList.value!!.add(
            WalletData(
                1020,
                1313,
                "Wallet 9",
                "12323",
                "200000",
                Currency(id = 555, "weq", "euro")
            )
        )

        hiddenWalletList.value!!.add(
            WalletData(
                1020,
                1313,
                "My wallet",
                "12323",
                "121100",
                Currency(id = 555, "weq", "euro")
            )
        )
    }

}