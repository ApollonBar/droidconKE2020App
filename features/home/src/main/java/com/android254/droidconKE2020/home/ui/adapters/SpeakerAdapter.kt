package com.android254.droidconKE2020.home.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.android254.droidconKE2020.home.R
import com.android254.droidconKE2020.home.domain.Speaker
import com.android254.droidconKE2020.home.ui.views.HomeFragmentDirections
import kotlinx.android.synthetic.main.home_item_speaker.view.*

class SpeakerAdapter : RecyclerView.Adapter<SpeakerAdapter.SpeakerViewHolder>() {

    private val speakers = mutableListOf<Speaker>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeakerViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.home_item_speaker, parent, false)
        return SpeakerViewHolder(view)
    }

    override fun getItemCount(): Int = speakers.size

    override fun onBindViewHolder(holder: SpeakerViewHolder, position: Int) {
        val speaker = speakers[position]
        holder.bindSpeaker(speaker)
    }

    fun updateData(list: List<Speaker>) {
        speakers.clear()
        speakers.addAll(list)
        notifyDataSetChanged()
    }

    inner class SpeakerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bindSpeaker(speaker: Speaker) {
            with(speaker) {
                view.speakerImg.load(speaker.imageUrl)
                view.name.text = name
                itemView.setOnClickListener { onSpeakerClicked(speaker.id) }
            }
        }

        private fun onSpeakerClicked(speakerId: Int) {
            val speakerDetailsAction =
                HomeFragmentDirections.actionHomeFragmentToSpeakerDetailsFragment()
            view.findNavController().navigate(speakerDetailsAction)
        }
    }
}