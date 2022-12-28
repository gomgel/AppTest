package com.pop.apptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

class MainActivity3 : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()



        findViewById<RecyclerView>(R.id.countriesList).apply {
            layoutManager = LinearLayoutManager(this@MainActivity3)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, Observer {countries ->
            countries?.let {
                //countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it) }
        })

        viewModel.countryLoadError.observe(this, Observer { isError ->
            findViewById<TextView>(R.id.list_error).visibility = if(isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                findViewById<ProgressBar>(R.id.loading_view).visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    findViewById<TextView>(R.id.list_error).visibility = View.GONE
                    //countriesList.visibility = View.GONE
                }
            }
        })
    }
}