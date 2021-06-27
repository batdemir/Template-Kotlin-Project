package com.batdemir.template.ui.view.github.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentGithubDetailBinding
import com.batdemir.template.ui.base.view.BaseFragment
import com.batdemir.template.ui.view.MainActivity

class GithubDetailFragment :
    BaseFragment<FragmentGithubDetailBinding, GithubDetailViewModel>(R.layout.fragment_github_detail) {
    private val args: GithubDetailFragmentArgs by navArgs()

    override fun inject() = (requireActivity() as MainActivity).githubComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        getBinding().model = args.model
    }

    override fun setupListener() {
        //("Not yet implemented")
    }
}