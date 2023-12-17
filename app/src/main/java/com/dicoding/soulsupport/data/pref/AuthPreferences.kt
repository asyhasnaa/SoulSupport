package com.dicoding.soulsupport.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.soulsupport.data.model.AuthModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
class AuthPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveToken(authModel: AuthModel) {
        dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = authModel.email.toString()
            preferences[NAME_KEY] = authModel.name.toString()
            preferences[TOKEN_KEY] = authModel.token.toString()
            preferences[IS_LOGIN_KEY] = true
        }
    }
    fun getSession(): Flow<AuthModel> {
        return dataStore.data.map { preference ->
            AuthModel(
                preference[EMAIL_KEY] ?: "",
                preference[NAME_KEY] ?: "",
                preference[TOKEN_KEY] ?: "",
                preference[IS_LOGIN_KEY] ?: false
            )
        }
    }
    suspend fun logout(){
        dataStore.edit { preference ->
            preference.clear()
        }
    }

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }




    companion object {
        @Volatile
        private var INSTANCE: AuthPreferences? = null

        private val EMAIL_KEY = stringPreferencesKey("email")
        private val NAME_KEY = stringPreferencesKey("name")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        //Theme
        private val THEME_KEY = booleanPreferencesKey("theme_setting")

        fun getInstance(dataStore: DataStore<Preferences>): AuthPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = AuthPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }


}