package com.viettel.vht.core.pref

import android.content.Context
import android.content.SharedPreferences
import com.vht_iot.vsmartsdk.utils.VDefine


class AppPreferences constructor(
     context: Context
) : RxPreferences {

    companion object {
        const val PARAM_BEARER = "Bearer "
        const val PREF_PARAM_USER_INFO = "PREF_PARAM_USER_INFO"
        const val PREF_PARAM_EMAIL = "PREF_PARAM_EMAIL"
        const val PREF_PARAM_PASSWORD = "PREF_PARAM_PASSWORD"
        const val PREF_PARAM_DEVICE_TOKEN = "PREF_PARAM_DEVICE_TOKEN"
        const val PREF_PARAM_ADMIN_TOKEN = "PREF_PARAM_ADDMIN_TOKEN"
        const val PREF_PARAM_CURRENT_HOME = "PREF_PARAM_CURRENT_HOME"

        @Volatile
        private var instance: AppPreferences? = null

        fun getInstance(  context: Context): AppPreferences =
            instance ?: synchronized(this) {
                instance ?: AppPreferences(context)
            }
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(
        VDefine.PREF_FILE_NAME,
        Context.MODE_PRIVATE
    )

    override fun get(key: String): String? {
        return mPrefs.getString(key, "")
    }

    override fun put(key: String, value: String) {
        val editor: SharedPreferences.Editor = mPrefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun getToken() = get(PREF_PARAM_USER_INFO)

    override fun getEmail() = get(PREF_PARAM_EMAIL)

    override fun getPassword(): String? {
        return mPrefs.getString(PREF_PARAM_PASSWORD, "")
    }

    override fun setUserToken(userToken: String, deviceToken: String) {
        put(PREF_PARAM_USER_INFO, PARAM_BEARER + userToken)
        if (deviceToken.isNotEmpty()) {
            put(PREF_PARAM_DEVICE_TOKEN, deviceToken)
        }
    }

    override fun setAdminToken(token: String) {
        val editor: SharedPreferences.Editor = mPrefs.edit()
        editor.putString(PREF_PARAM_ADMIN_TOKEN, PARAM_BEARER + token)
        editor.apply()
    }

    override fun getAdminToken(): String? = get(PREF_PARAM_ADMIN_TOKEN)

    override fun setCurrentHome(homeId: String) {
        val editor: SharedPreferences.Editor = mPrefs.edit()
        editor.putString(PREF_PARAM_CURRENT_HOME, homeId)
        editor.apply()
    }

    override fun getCurrentHome(): String? = get(PREF_PARAM_CURRENT_HOME)
    override fun getDeviceToken(): String? {
        return mPrefs.getString(PREF_PARAM_PASSWORD, "")
    }

}