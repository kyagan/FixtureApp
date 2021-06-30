package com.kaanyagan.fixtureapp.di

import android.content.Context
import com.kaanyagan.fixtureapp.base.AppConstants
import com.kaanyagan.fixtureapp.datasource.local.LocalDataSource
import com.kaanyagan.fixtureapp.datasource.remote.RemoteDataSource
import com.kaanyagan.fixtureapp.network.APIService
import com.kaanyagan.fixtureapp.persistence.AppDao
import com.kaanyagan.fixtureapp.persistence.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyHelper {
    private lateinit var context: Context
    private lateinit var database: AppDatabase

    fun init(mContext: Context) {
        this.context = mContext
        this.database = getDatabase()
    }

    fun getDatabase(): AppDatabase {
        return AppDatabase.getInstance(this.context)
    }

    fun getDao(): AppDao {
        return database.appDao()
    }

    fun getLocaleDataSource(): LocalDataSource {
        return LocalDataSource(getDao())
    }

    fun getRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource(getApiService())
    }

    fun getApiService(): APIService {
        return getRetrofitInstance().create(APIService::class.java)
    }

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}