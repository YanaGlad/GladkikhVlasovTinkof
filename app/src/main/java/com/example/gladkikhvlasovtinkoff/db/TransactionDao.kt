package com.example.gladkikhvlasovtinkoff.db

import androidx.room.*
import com.example.gladkikhvlasovtinkoff.db.entity.TransactionDB
import com.example.gladkikhvlasovtinkoff.db.entity.TransactionWithCurrency
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(item : TransactionDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransactions(items : List<TransactionDB>)

    @Query(value = "Select * from `transaction` where walletId = :walletId order by date desc")
    fun getAllTransactionsByWalletId(walletId : Long) : Flowable<List<TransactionWithCurrency>>

    @Query(value = "Select * from `transaction` where id =:transactionId")
    fun getTransactionById(transactionId : Long) : Single<TransactionWithCurrency>

    @Delete()
    fun deleteTransaction(item : TransactionDB)

    @Update
    fun updateTransaction(item : TransactionDB)
}