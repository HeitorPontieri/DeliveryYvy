package br.senai.sp.jandira.deliveryyvy.services.datastore


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class TokenStore(private val context: Context) {
    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        val USER_TOKEN_KEY = stringPreferencesKey("user_token")
    }


    val getToken: Flow<String> = context.dataStore.data.map { preferences -> preferences[USER_TOKEN_KEY] ?: "" }


    //save email into datastore
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }
}