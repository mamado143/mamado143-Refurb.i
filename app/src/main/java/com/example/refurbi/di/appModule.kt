package org.refurbi.app.di

import org.refurbi.app.domain.repository.Repository
import com.example.refurbi.presentation.viewmodels.MainViewModel
import org.koin.dsl.module

val appModule = module {
    single { Repository() }
    single {
        MainViewModel(get())
    }
}