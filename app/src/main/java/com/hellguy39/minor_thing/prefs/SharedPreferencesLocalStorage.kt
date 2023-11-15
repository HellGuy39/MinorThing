package com.hellguy39.minor_thing.prefs

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesLocalStorage
@Inject
constructor(
    @ApplicationContext context: Context
) {

    private val storage by lazy {
        context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    }

    private fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(storage.edit()) {
            block.invoke(this)
            apply()
        }
    }

    var authenticatedUserid: Int
        get() = storage.getInt(Keys.authenticatedUserIdKey, -1)
        set(value) = edit { putInt(Keys.authenticatedUserIdKey, value) }

    object Keys {
        const val authenticatedUserIdKey = "authenticatedUserIdKey"
    }

    object DefaultValues {
        const val emptyUserId = -1
    }

    companion object {
        const val STORAGE_NAME = "local_shared_prefs"
    }
}