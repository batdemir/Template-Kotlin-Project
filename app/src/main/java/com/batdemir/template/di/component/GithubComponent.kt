package com.batdemir.template.di.component

import com.batdemir.template.ui.view.github.GithubFragment
import dagger.Subcomponent

@Subcomponent
interface GithubComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): GithubComponent
    }

    fun inject(githubFragment: GithubFragment)
}