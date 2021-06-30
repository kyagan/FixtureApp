package com.kaanyagan.fixtureapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kaanyagan.fixtureapp.R;
import com.kaanyagan.fixtureapp.model.TeamModel;

class TeamListAdapter(
    private val context: Context,
    var teamList: List<TeamModel>,
    var clickListener: ItemClickListener
) : RecyclerView.Adapter<TeamListAdapter.MyViewHolder>() {


    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(teamList.get(position), clickListener)
    }

    class MyViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(teamItem: TeamModel, clickListener: ItemClickListener) {
            val textView = itemView.findViewById<TextView>(R.id.nameView)
            val parent = itemView.findViewById<LinearLayout>(R.id.linear_root_row)
            textView.setText(teamItem.name)
            parent.setOnClickListener(View.OnClickListener {
                clickListener.onTeamClick(teamItem)
            })
        }

    }

    interface ItemClickListener {
        public fun onTeamClick(team: TeamModel);
    }


}
