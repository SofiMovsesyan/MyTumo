package org.tumo.MyTumo.service.login

import android.content.Context
import android.content.SharedPreferences

object TokenHolder {
    private var sharedPreferences: SharedPreferences? = null
    private const val TOKEN_KEY = "AUTH_TOKEN"
    private const val PREFERENCE_KEY = "MyAppPreferences"

    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
    }

    var token: String?
        get() = sharedPreferences?.getString(TOKEN_KEY, null)
        set(value) {
            sharedPreferences?.edit()?.putString(TOKEN_KEY, value)?.apply()
        }
}
