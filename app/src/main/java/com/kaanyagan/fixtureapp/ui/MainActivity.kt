package com.kaanyagan.fixtureapp.ui;


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kaanyagan.fixtureapp.adapter.TeamListAdapter
import com.kaanyagan.fixtureapp.databinding.ActivityMainBinding
import com.kaanyagan.fixtureapp.di.DependencyHelper
import com.kaanyagan.fixtureapp.model.TeamModel
import com.kaanyagan.fixtureapp.viewmodel.TeamListViewModel


class MainActivity : AppCompatActivity(), TeamListAdapter.ItemClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var teamViewModell: TeamListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        teamViewModell = TeamListViewModel(DependencyHelper.getLocaleDataSource(),DependencyHelper.getRemoteDataSource())
        initObservers()
        teamViewModell.getAllTeams()
        onClick()


    }

    private fun initObservers() {
        teamViewModell.teamList.observe(this, Observer {
            setRecycler(it)
        })
    }

    private fun onClick() {
        binding.btnDraw.setOnClickListener {
            val homepage = Intent(this, FixtureActivity::class.java)
            startActivity(homepage)
        }
    }


    private fun setRecycler(list: List<TeamModel>) {
        val adapter = TeamListAdapter(this, list, this)
        binding.recyclerView.adapter = adapter

    }

    override fun onTeamClick(team: TeamModel) {
        Toast.makeText(this, "Clicked: " + team.name, Toast.LENGTH_SHORT).show();
    }
}