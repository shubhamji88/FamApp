package com.shubham.famapp.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.shubham.famapp.BuildConfig
import com.shubham.famapp.domain.model.CardGroupModel
import com.shubham.famapp.domain.model.CardModel

class SharedPrefManager(val context: Context) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SP_NAME, ACCESS_MODE)
    private val gson = Gson()

    private fun setString(key: String?, value: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
    private fun getString(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }
    var blockedCards: List<Int>?
        get() {
            return getString(BLOCKED_CARD_LIST)?.let {
                if (it.isBlank()) null else
                    Gson().fromJson(it, Array<Int>::class.java).toList()
            }
        }
        set(blockedList) {
            if (blockedList != null) {
                setString(
                    BLOCKED_CARD_LIST,
                    gson.toJson(blockedList)
                )
            }
        }

    var snoozedCards: List<Int>?
        get() {
            return getString(SNOOZED_CARD_LIST)?.let {
                if (it.isBlank()) null else
                    Gson().fromJson(it, Array<Int>::class.java).toList()
            }
        }
        set(blockedList) {
            if (blockedList != null) {
                setString(
                    SNOOZED_CARD_LIST,
                    gson.toJson(blockedList)
                )
            }
        }
    companion object {
        private const val SP_NAME = BuildConfig.APPLICATION_ID
        private const val ACCESS_MODE = Context.MODE_PRIVATE
        const val BLOCKED_CARD_LIST = "BLOCKED_CARD_LIST"
        const val SNOOZED_CARD_LIST = "SNOOZED_CARD_LIST"
        @JvmStatic
        lateinit var instance: SharedPrefManager
            private set

        fun createInstance(context: Context): SharedPrefManager {
            if (!::instance.isInitialized) {
                instance = SharedPrefManager(context)
            }
            return instance
        }
    }
}