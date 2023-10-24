package com.example.group2_comp304lab2_ex1

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

open class HomeTypeMenu : AppCompatActivity() {
    lateinit var recyclerViewData: ArrayList<HomeRecyclerViewData>

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.hometypes, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection.
        return when (item.itemId) {
            R.id.menu_appartment -> {
                showApartmentView()
                true
            }
            R.id.menu_detached_home -> {
                showDetachedHomeView()
                true
            }
            R.id.menu_condominium_appartment -> {
                showCondoView()
                true
            }
            R.id.menu_town_house -> {
                showTownHouseView()
                true
            }
            R.id.menu_semi_detached_home -> {
                showSemiDetachedHouseView()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun showApartmentView() {
        val k = Intent(this, TypeApartment::class.java)
        startActivity(k)
    }

    private fun showCondoView() {
        val k = Intent(this, TypeCondo::class.java)
        startActivity(k)
    }

    private fun showDetachedHomeView() {
        val k = Intent(this, TypeDetachedHome::class.java)
        startActivity(k)
    }

    private fun showTownHouseView() {
        val k = Intent(this, TypeTownHouse::class.java)
        startActivity(k)
    }

    private fun showSemiDetachedHouseView() {
        val k = Intent(this, TypeSemiDetachedHome::class.java)
        startActivity(k)
    }

    protected fun loadData(
        view: RecyclerView,
        recyclerViewData: ArrayList<HomeRecyclerViewData>
    ) {
        val preferenceName:String = resources.getString(R.string.preference_name)
        val sharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val checkedItems = ArrayList(sharedPreferences.all.keys)

        // create a vertical layout manager
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // create recycler view adapter
        val homeRecyclerViewAdapter =
            HomeRecyclerViewAdapter(recyclerViewData, assets, checkedItems)

        view.adapter = homeRecyclerViewAdapter
        view.layoutManager = layoutManager

        homeRecyclerViewAdapter.notifyDataSetChanged()
    }

    fun onCheckOutButtonClick(view: View) {

        if (this::recyclerViewData.isInitialized){
            val checkedItems = ArrayList<HomeRecyclerViewData>()
            val preferenceName:String = resources.getString(R.string.preference_name)
            val sharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            for (item in recyclerViewData) {
                if (item.isChecked) {
                    checkedItems.add(item)
                    editor.putFloat(item.address, item.price)
                } else {
                    editor.remove(item.address)
                }
            }
            editor.apply()

            // if user checked anything, show activity
            if (sharedPreferences.all.keys.size > 0) {
                val k = Intent(this, CheckOut::class.java)
                startActivity(k)
            } else {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle("Warning")
                    .setMessage("Please pick a Home")
                    .setPositiveButton("OK") { _, _ -> }
                    .show()
            }
        }
    }

    override fun onPause() {
        if (this::recyclerViewData.isInitialized){
            val checkedItems = ArrayList<HomeRecyclerViewData>()
            val preferenceName:String = resources.getString(R.string.preference_name)
            val sharedPreferences = getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            for (item in recyclerViewData) {
                if (item.isChecked) {
                    checkedItems.add(item)
                    editor.putFloat(item.address, item.price)
                } else {
                    editor.remove(item.address)
                }
            }

            editor.apply()
        }

        super.onPause()
    }

}