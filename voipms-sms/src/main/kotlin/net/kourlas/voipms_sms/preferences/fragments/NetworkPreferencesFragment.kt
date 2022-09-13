/*
 * VoIP.ms SMS
 * Copyright (C) 2018-2019 Michael Kourlas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.kourlas.voipms_sms.preferences.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.Preference
import com.takisoft.preferencex.EditTextPreference
import com.takisoft.preferencex.PreferenceFragmentCompat
import net.kourlas.voipms_sms.R
import net.kourlas.voipms_sms.utils.preferences

class NetworkPreferencesFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreatePreferencesFix(
        savedInstanceState: Bundle?,
        rootKey: String?
    ) {
        // Add preferences
        addPreferencesFromResource(R.xml.preferences_network)

        // Add listener for preference changes
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(
            this
        )

        // Update preferences summaries
        updateSummaries()
    }

    override fun onResume() {
        super.onResume()

        // Update preferences summaries
        updateSummaries()
    }

    /**
     * Updates the summary text for all preferences.
     */
    private fun updateSummaries() {
        for (preference in preferenceScreen.preferences) {
            updateSummaryTextForPreference(preference)
        }
    }

    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences,
        key: String
    ) {
        // It's not clear why onSharedPreferenceChanged is called before the
        // fragment is actually added to the activity, but it apparently is;
        // this check is therefore required to prevent a crash
        if (isAdded) {
            // Update summary text for changed preference
            updateSummaryTextForPreference(findPreference(key))
        }
    }

    /**
     * Updates the summary text for the specified preference.
     */
    private fun updateSummaryTextForPreference(preference: Preference?) {
        if (preference is EditTextPreference) {
            // Display value of preference as summary text
            if (preference.key == getString(
                    R.string.preferences_network_connect_timeout_key
                )
                || preference.key == getString(
                    R.string.preferences_network_read_timeout_key
                )
            ) {
                try {
                    if (preference.text?.toInt() == 0) {
                        preference.summary = "Infinite"
                    } else {
                        preference.summary = preference.text + " seconds"
                    }
                } catch (e: NumberFormatException) {
                    preference.summary = "Infinite"
                }
            } else {
                preference.summary = preference.text
            }
        }
    }
}