package com.dicoding.soulsupport.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.dicoding.soulsupport.R
import com.dicoding.soulsupport.ui.notification.DailyReminder
import com.dicoding.soulsupport.utils.NightMode
import java.util.Locale

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val theme = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        theme?.setOnPreferenceChangeListener{ _, newValue ->
            val themeMode = NightMode.valueOf(newValue.toString().toUpperCase(Locale.US))
            updateTheme(themeMode.value)
            true
        }

        val prefNotification = findPreference<SwitchPreference>(getString(R.string.pref_key_notify))
        val dailyReminder = DailyReminder()
        prefNotification?.setOnPreferenceChangeListener {_, value ->
            if (value == true) {
                dailyReminder.setDailyReminder(requireContext())
            } else {
                dailyReminder.cancelAlarm(requireContext())
            }

            true
        }
  }

    private fun updateTheme(nightMode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(nightMode)
        requireActivity().recreate()
        return true
    }
}