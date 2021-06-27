package com.batdemir.template.di.component

import com.batdemir.template.ui.view.stackoverflow.StackOverFlowFragment
import com.batdemir.template.ui.view.stackoverflow.detail.StackOverFlowDetailFragment
import dagger.Subcomponent

@Subcomponent
interface StackOverFlowComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): StackOverFlowComponent
    }

    fun inject(stackOverFlowFragment: StackOverFlowFragment)
    fun inject(stackOverFlowDetailFragment: StackOverFlowDetailFragment)
}