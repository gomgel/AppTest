package com.pop.apptest

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pop.apptest.databinding.ItemCustomDialogBinding

class CustomDialogAdapter(var items : List<NameInfo>, val itemClick : (NameInfo, Int, CustomDialogAdapter) -> Unit) : RecyclerView.Adapter<CustomDialogAdapter.ViewHolder>() {

    //private var items : List<NameInfo> = emptyList()
    lateinit var binding: ItemCustomDialogBinding

    private var bBatch : Boolean = false
    private var iLastIndex : Int = 0;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCustomDialogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(this, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(list : List<NameInfo>) {
        items = list
        notifyDataSetChanged()
    }

    fun changeMode(param : Boolean) {
        if (param != bBatch) bBatch = param

        iLastIndex = 0
        items.forEach { it.index = -1; }
        notifyDataSetChanged()
    }

    fun clearNumbering() {
        iLastIndex = 0;
        items.forEach { it.index = -1; }
        notifyDataSetChanged()
    }

    inner class ViewHolder(val adapter: CustomDialogAdapter, private val binding : ItemCustomDialogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : NameInfo, position: Int) {

            binding.itemName.text = item.Name
            binding.itemProcess.text = item.process
            binding.itemNmbr.isVisible = bBatch
            binding.itemNmbr.text = if (item.index == -1) {""} else {item.index.toString()}

            binding.cardView.setOnClickListener {
                itemClick.let {
                    if (!bBatch)
                        itemClick(item, position, adapter)
                    else {
                        iLastIndex += 1
                        binding.itemNmbr.text = iLastIndex.toString()
                    }
                }
            }

            if (item.process.length == 0)
                binding.itemImage.setColorFilter(
                    Color.parseColor("#6D6D6D"),
                    PorterDuff.Mode.SRC_IN
                )
            else
                binding.itemImage.setColorFilter(
                    Color.parseColor("#00B99C"),
                    PorterDuff.Mode.SRC_IN
                )
        }
    }

}