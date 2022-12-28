package com.pop.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pop.apptest.databinding.ActivityMain4Binding
import com.pop.apptest.iftest.Activity4Adapter

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityMain4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewMain.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.recyclerviewMain.adapter = Activity4Adapter()

//        recyclerView.layoutManager = layoutManager
//        var adapter = MyAdapter()
//        recyclerView.adapter = adapter

    }
}