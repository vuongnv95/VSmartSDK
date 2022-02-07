package com.viettel.vht.core.pref


interface RxPreferences {
    fun put(key: String, value: String)
    fun get(key: String): String?
    fun getToken(): String?
    fun getEmail(): String?
    fun getPassword(): String?
    fun setUserToken(userToken: String, deviceToken: String = "")
    fun setAdminToken(token: String)
    fun getAdminToken(): String?
    fun setCurrentHome(homeId: String)
    fun getCurrentHome(): String?
    fun getDeviceToken(): String?
}