package com.dovohmichael.fixerapp.domain.entity

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {

    class CurrencyException(cause: Throwable) : UseCaseException(cause)

    class RateException(cause: Throwable) : UseCaseException(cause)

    class HistoryRateException(cause: Throwable) : UseCaseException(cause)

    class UnknownException(cause: Throwable) : UseCaseException(cause)


    companion object {

        fun createFromThrowable(throwable: Throwable): UseCaseException {
            return if (throwable is UseCaseException) throwable else UnknownException(throwable)
        }
    }
}