package com.pop.apptest

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.pop.apptest.databinding.ItemWorkerBinding
import com.pop.apptest.iftest.Config

class Adater(val todoItemClick : (NameInfo, Int, Adater) -> Unit, val todoItemLongClick : (NameInfo, Int, Adater) -> Unit ) : RecyclerView.Adapter<Adater.ViewHolder>(){

    private var items : List<NameInfo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWorkerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(this, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setTodos(todos : List<NameInfo>){
        this.items = todos
        notifyDataSetChanged()
    }

    fun changedData(){
        notifyDataSetChanged()
    }

    inner class ViewHolder(val adater: Adater, val binding : ItemWorkerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : NameInfo) {

            binding.itemName.text = item.Name
            binding.itemProcess.text = item.process

            if (item.process.length == 0)
                binding.itemImage.setColorFilter(Color.parseColor("#6D6D6D"), PorterDuff.Mode.SRC_IN)
            else
                binding.itemImage.setColorFilter(Color.parseColor("#00B99C"), PorterDuff.Mode.SRC_IN)


            binding.root.setOnClickListener {
                todoItemClick( item, this.adapterPosition, adater)
            }

            binding.root.setOnLongClickListener {
                todoItemLongClick( item, this.adapterPosition, adater)
                true
            }

            binding.itemImage.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    Log.d(Config.LOG_TAG, "ImageView Clicked.....")
                }
            })
        }
    }
}