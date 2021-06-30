@file:Suppress("unused")


package com.kaanyagan.fixtureapp.base

import android.app.Application
import androidx.multidex.MultiDex
import com.kaanyagan.fixtureapp.di.DependencyHelper

public class BaseApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this);
        DependencyHelper.init(this)

    }
}