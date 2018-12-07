package fr.ydelerm.verybestof.persistence;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    private static final String PREFERENCE_FILE_KEY = "prefsFile";
    private static final String LAST_UPDATE_TIMESTAMP = "lastUpdate";
    private final SharedPreferences sharedPref;

    public PrefsManager(Context context) {
        sharedPref = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
    }

    public long getLastUpdateTimestamp() {
        return sharedPref.getLong(LAST_UPDATE_TIMESTAMP, 0);
    }

    public void updateLastUpdateTimestamp(long requestTimestamp) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(LAST_UPDATE_TIMESTAMP, requestTimestamp);
        editor.apply();
    }
}
