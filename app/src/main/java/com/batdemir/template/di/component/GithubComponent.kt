package com.batdemir.template.di.component

import com.batdemir.template.ui.view.github.GithubFragment
import com.batdemir.template.ui.view.github.detail.GithubDetailFragment
import dagger.Subcomponent

@Subcomponent
interface GithubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): GithubComponent
    }

    fun inject(githubFragment: GithubFragment)
    fun inject(githubDetailFragment: GithubDetailFragment)
}