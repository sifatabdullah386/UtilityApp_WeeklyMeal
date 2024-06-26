package com.example.weeklymeal.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
	// LogCat tag
	private static String TAG = SessionManager.class.getSimpleName();

	// Shared Preferences
	SharedPreferences pref;

	Editor editor;
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Shared preferences file name
	private static final String PREF_NAME = "BuildersAppLogin";
	
	private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

	public SessionManager(Context context) {
		this._context = context;
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}

	public void setLogin(boolean isLoggedIn) {

		editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);

		// commit changes
		editor.commit();

		Log.d(TAG, "User session modified!");
	}

	
	public boolean isLoggedIn(){
		return pref.getBoolean(KEY_IS_LOGGED_IN, false);
	}

	public void setValue(String Name, String SesValue) {

		editor.putString(Name, SesValue);

		// commit changes
		editor.commit();

	}

	public String getValue(String Name) {

		String Req = pref.getString(Name,"");
		return Req;

	}

	public void setValueInt(String Name, int SesValue) {

		editor.putInt(Name, SesValue);

		// commit changes
		editor.commit();
	}
	public int getValueInt(String Name) {

		int Req = pref.getInt(Name,0);
		return Req;

	}
	public void ClearAll() {
		editor.clear();
	}
}
