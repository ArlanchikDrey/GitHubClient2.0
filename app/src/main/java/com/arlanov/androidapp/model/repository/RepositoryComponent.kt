package com.arlanov.androidapp.model.repository

import com.arlanov.androidapp.vm.ListViewModel
import dagger.Component

@Component
interface RepositoryComponent {
    fun inject(listViewModel: ListViewModel)
}