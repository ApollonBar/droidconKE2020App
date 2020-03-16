package com.android254.droidconKE2020.home.ui.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android254.droidconKE2020.home.R
import kotlinx.android.synthetic.main.item_organizer.view.*

class OrganizerAdapter : RecyclerView.Adapter<OrganizerAdapter.OrganizerViewHolder>() {

    private val organizers = mutableListOf<Organizer>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_organizer, parent, false)
        return OrganizerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrganizerViewHolder, position: Int) {
        val org = organizers[position]
        holder.bindOrg(org)
    }

    override fun getItemCount(): Int = organizers.size

    fun updateData(list: List<Organizer>) {
        organizers.clear()
        organizers.addAll(list)
        notifyDataSetChanged()
    }

    inner class OrganizerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orgImg: ImageView

        init {
            orgImg = view.organizerImg
        }

        fun bindOrg(organizer: Organizer) {
            with(organizer) {

            }
        }
    }
}