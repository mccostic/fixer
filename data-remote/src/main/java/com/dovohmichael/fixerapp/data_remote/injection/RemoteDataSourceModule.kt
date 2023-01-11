package com.dovohmichael.fixerapp.data_remote.injection


import com.dovohmichael.fixerapp.data_remote.source.RemoteCurrencyDataSourceImpl
import com.dovohmichael.fixerapp.data_remote.source.RemoteHistoryRateDataSourceImpl
import com.dovohmichael.fixerapp.data_remote.source.RemoteRateDataSourceImpl
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteCurrencyDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteHistoryRateDataSource
import com.dovohmichael.fixerapp.data_repository.data_source.remote.RemoteRateDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindCurrencyDataSource(remoteCurrencyDataSourceImpl: RemoteCurrencyDataSourceImpl): RemoteCurrencyDataSource


    @Binds
    abstract fun bindRateDataSource(remoteRateDataSourceImpl: RemoteRateDataSourceImpl): RemoteRateDataSource

    @Binds
    abstract fun bindHistoryRateDataSource(remoteHistoryRateDataSourceImpl: RemoteHistoryRateDataSourceImpl): RemoteHistoryRateDataSource


}