package com.pop.apptest.iftest

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pop.apptest.R

class Activity4Adapter() : RecyclerView.Adapter<Activity4Adapter.MyViewHolder>() {
    var titles = arrayOf("one", "two", "three", "four", "five")
    var details = arrayOf("Item one", "Item two", "Item three", "Item four", "Itme five")

    var images = intArrayOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground)


    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        public var itemimage: ImageView = itemview.findViewById<ImageView>(R.id.item_image)
        public var itemtitle: TextView = itemview.findViewById<TextView>(R.id.item_title)//itemview.item_title
        public var itemdetail: TextView = itemview.findViewById<TextView>(R.id.item_detail)//itemview.item_detail
    }

    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): MyViewHolder {
        var v: View = LayoutInflater.from(viewgroup.context).inflate(R.layout.item_card, viewgroup, false)

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemtitle.text = titles.get(position)
        holder.itemimage.setImageResource(images.get(position))
        holder.itemdetail.text = details.get(position)

        if ( (position % 2) == 0 ) {
            holder.itemimage.setColorFilter(Color.parseColor("#55ff00"), PorterDuff.Mode.SRC_IN)
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }
}