package com.example.bookalarm.SETTING;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.widget.BaseAdapter;

import androidx.annotation.Nullable;

import com.example.bookalarm.R;

public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences prefs;
    ListPreference lp1_set;
    PreferenceScreen ps1_set;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings_preference);
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        ps1_set = (PreferenceScreen)findPreference("ps1_set");
        lp1_set = (ListPreference)findPreference("lp1_set");

        if(!prefs.getString("lp1_set","").equals("")) lp1_set.setSummary(prefs.getString("lp1_set","일주일전"));

        if(!prefs.getBoolean("ps1_set",false)) ps1_set.setSummary("사용 안함");

        prefs.registerOnSharedPreferenceChangeListener(prefListener);
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("lp1_set")) lp1_set.setSummary(prefs.getString("lp1_set","일주일전"));

            if(key.equals("sp2_set")){
                if(prefs.getBoolean("sp2_set",false)) ps1_set.setSummary("사용");
                else ps1_set.setSummary("안함");
            }

            ((BaseAdapter)getPreferenceScreen().getRootAdapter()).notifyDataSetChanged();
        }
    };
}
