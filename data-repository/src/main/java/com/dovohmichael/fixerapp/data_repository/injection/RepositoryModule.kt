package com.dovohmichael.fixerapp.data_repository.injection


import com.dovohmichael.fixerapp.data_repository.repository.CurrencyRepositoryImpl
import com.dovohmichael.fixerapp.data_repository.repository.HistoryRateRepositoryImpl
import com.dovohmichael.fixerapp.data_repository.repository.RateRepositoryImpl
import com.dovohmichael.fixerapp.domain.repository.CurrencyRepository
import com.dovohmichael.fixerapp.domain.repository.HistoryRateRepository
import com.dovohmichael.fixerapp.domain.repository.RateRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository

    @Binds
    abstract fun bindRateRepository(rateRepositoryImpl: RateRepositoryImpl): RateRepository


    @Binds
    abstract fun bindHistoryRateRepository(historyRateRepositoryImpl: HistoryRateRepositoryImpl): HistoryRateRepository

}