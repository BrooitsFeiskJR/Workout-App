package dev.tontech.workoutapp.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import dev.tontech.workoutapp.R
import dev.tontech.workoutapp.databinding.ItemHistoryBinding

class HistoryAdapter(private val items: ArrayList<String>): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items[position]
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        if (position % 2 == 0) {
            holder.llHistoryItemMain.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.colorLightGrey))
        } else {
            holder.llHistoryItemMain.setHorizontalGravity(Color.parseColor("#FFFFFF"))
        }
    }
}