package com.batdemir.template.features.github.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.batdemir.core.view.BaseFragment
import com.batdemir.template.R
import com.batdemir.template.databinding.FragmentGithubDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubDetailFragment :
    BaseFragment<FragmentGithubDetailBinding, GithubDetailViewModel>(R.layout.fragment_github_detail) {
    private val viewModel: GithubDetailViewModel by viewModels()
    private val args: GithubDetailFragmentArgs by navArgs()

    override fun setupDefinition(savedInstanceState: Bundle?) {
        setupViewModel(viewModel)
        getBinding().model = args.model
    }

    override fun setupListener() {
        //("Not yet implemented")
    }
}