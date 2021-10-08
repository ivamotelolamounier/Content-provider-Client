package com.ivamotelo.aplicationcontentproviderclient

import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var notesRecycler : RecyclerView
    lateinit var notesRefreshButoon : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRecycler = findViewById(R.id.client_list)
        notesRefreshButoon = findViewById(R.id.client_button_refresh)

        notesRefreshButoon.setOnClickListener { getContentProvider()}

    }
    private fun getContentProvider(){
        try {
            val url = "content://com.ivamotelo.applicationcontentprovider.provider/notes"
            val data = Uri.parse(url)
            val cursor : Cursor? = contentResolver.query(data, null, null, null, "tilte")
            notesRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = ClientAdapter(cursor as Cursor)
            }
        } catch (ex: Exception){
            ex.printStackTrace()
        }

    }
}