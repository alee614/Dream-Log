package com.example.dreamlog

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DreamListAdapter : ListAdapter<Dream, DreamListAdapter.DreamViewHolder>(DreamComparator()){
    override fun onBindViewHolder(holder: DreamListAdapter.DreamViewHolder, position: Int) {
        val currentDream = getItem(position)
        holder.bindText(currentDream.id.toString(), holder.idTextView)
        holder.bindText(currentDream.title, holder.titleTextView)

        holder.constraintLayout.setOnClickListener{
            val intent = Intent(holder.constraintLayout.context, DreamActivity::class.java)
            intent.putExtra("id", currentDream.id)
            startActivity(holder.constraintLayout.context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
        return DreamViewHolder.create(parent)
    }

    class DreamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout_dream)
        val idTextView: TextView = itemView.findViewById(R.id.textView_id)
        val titleTextView:TextView = itemView.findViewById(R.id.textView_title)


        fun bindText(text:String?, textView: TextView){
            textView.text = text
        }
        companion object{
            fun create(parent:ViewGroup): DreamViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dream, parent, false)
                return DreamViewHolder(view)
            }
        }
    }

    class DreamComparator : DiffUtil.ItemCallback<Dream>(){
        override fun areContentsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem === newItem
        }
    }
}