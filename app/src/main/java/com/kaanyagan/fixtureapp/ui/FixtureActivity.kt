package com.kaanyagan.fixtureapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kaanyagan.fixtureapp.R
import com.kaanyagan.fixtureapp.databinding.ActivityFixtureBinding
import com.kaanyagan.fixtureapp.di.DependencyHelper
import com.kaanyagan.fixtureapp.viewmodel.FixtureViewModel

class FixtureActivity : AppCompatActivity() {

    lateinit var binding: ActivityFixtureBinding

    lateinit var viewModel: FixtureViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFixtureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = FixtureViewModel(DependencyHelper.getLocaleDataSource())
    }
}