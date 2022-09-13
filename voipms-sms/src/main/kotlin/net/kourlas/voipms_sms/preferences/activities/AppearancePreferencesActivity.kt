/*
 * VoIP.ms SMS
 * Copyright (C) 2020 Michael Kourlas
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

package net.kourlas.voipms_sms.preferences.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import net.kourlas.voipms_sms.R
import net.kourlas.voipms_sms.preferences.fragments.AppearancePreferencesFragment

/**
 * Activity that houses a PreferencesFragment that displays the appearance
 * preferences.
 */
class AppearancePreferencesActivity : AppCompatActivity() {
    // Preferences fragment for this preferences activity
    private lateinit var fragment: AppearancePreferencesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load activity layout
        setContentView(R.layout.preferences_appearance)

        // Configure toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }

        // Load preferences fragment
        if (savedInstanceState == null) {
            fragment = AppearancePreferencesFragment()
            supportFragmentManager.beginTransaction().replace(
                R.id.preferences_fragment_layout, fragment
            ).commit()
        }
    }
}