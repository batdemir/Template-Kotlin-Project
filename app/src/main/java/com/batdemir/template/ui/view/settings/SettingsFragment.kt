package com.batdemir.template.ui.view.settings

import android.content.Context
import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.batdemir.template.R
import com.batdemir.template.ui.base.BaseAction
import com.batdemir.template.ui.view.MainActivity
import com.batdemir.template.utils.reset
import javax.inject.Inject

class SettingsFragment :
    PreferenceFragmentCompat(), BaseAction {
    @Inject
    lateinit var viewModel: SettingsViewModel
    private lateinit var languagePreference: ListPreference
    private lateinit var themePreference: SwitchPreference
    private lateinit var versionPreference: Preference
    private lateinit var privacyPreference: Preference

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference, rootKey)
        setupDefinition(savedInstanceState)
        setupData()
        setupListener()
    }

    override fun inject() = (requireActivity() as MainActivity).settingsComponent.inject(this)

    override fun setupDefinition(savedInstanceState: Bundle?) {
        languagePreference = findPreference(getString(R.string.KEY_LANGUAGE))!!
        themePreference = findPreference(getString(R.string.KEY_THEME))!!
        versionPreference = findPreference(getString(R.string.KEY_VERSION))!!
        privacyPreference = findPreference(getString(R.string.KEY_PRIVACY))!!
    }

    override fun setupData() {
        versionPreference.summary = viewModel.getVersionName()
    }

    override fun setupListener() {
        languagePreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                viewModel.changeLanguage(newValue = newValue.toString())
                requireActivity().reset()
                true
            }
        themePreference.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { _, newValue ->
                viewModel.changeTheme(newValue = newValue as Boolean)
                true
            }
    }
}