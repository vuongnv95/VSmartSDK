package com.example.core.utils

import androidx.annotation.StringRes
import com.example.core.R

object Constants {
    const val PREF_FILE_NAME = "Picking.Preferences"
    const val DEFAULT_TIMEOUT = 30
    const val DURATION_TIME_CLICKABLE = 500

    //splash
    const val DURATION_TIME_SPLASH = 300
    const val DURATION_ANIM_CHANGE_SCREEN = 300L
    const val TYPE_SETTING_TITLE = 1
    const val TYPE_SETTING_CONTENT = 0

    object NetworkRequestCode {
        const val REQUEST_CODE_200 = 200    //normal
        const val REQUEST_CODE_400 = 400    //parameter error
        const val REQUEST_CODE_401 = 401    //unauthorized error
        const val REQUEST_CODE_403 = 403
        const val REQUEST_CODE_404 = 404    //No data error
        const val REQUEST_CODE_500 = 500    //system error
    }

    object ApiComponents {
        const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"
    }

    enum class Settings {
        SUPPORT(
            R.string.support,
            TYPE_SETTING_TITLE
        ),

        //        SUPPORT_INFO(R.string.support_information) ,
        HELP(R.string.help),
        GUID_USE(R.string.guid_to_use_app),
        PRIVACY_POLICY(R.string.privacy_policy),
        APP_INFO(
            R.string.app_info,
            TYPE_SETTING_TITLE
        ),
        VERSION(R.string.version),
        TERM_SERVICE(R.string.term_of_service);

        @StringRes
        var stringId: Int
        var typeSetting: Int

        constructor(stringId: Int) {
            this.stringId = stringId
            typeSetting = TYPE_SETTING_CONTENT
        }

        constructor(stringId: Int, type: Int) {
            this.stringId = stringId

            typeSetting = type
        }
    }
}
