package com.example.moviebooktracker

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private val items = mutableListOf(
        MyListItem(R.drawable.book,"Reksio","Dla Dzieci","fajny","6"),
        MyListItem(R.drawable.movie,"Krecik","Dla Dzieci","Śmieszny","10")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        val addButton = findViewById<Button>(R.id.add_item_button)
        val myListView: ListView = findViewById(R.id.listView)
        val radio_Group = findViewById<RadioGroup>(R.id.radioGroup)
        val newItemEditT = findViewById<EditText>(R.id.my_title)
        val newItemEditD = findViewById<EditText>(R.id.my_description)
        val newItemEditR = findViewById<EditText>(R.id.my_review)
        var selectedImageResId = R.drawable.book

        //-----------------------DZIAŁANIE_Adaptera---------------------------
        val adapter = MyCustomAdapter(this, R.layout.custom_list_item, items)
        myListView.adapter = adapter

        //-----------------------DZIAŁANIE_Seek_Bar------------------------
        val Seek_Bar = findViewById<SeekBar>(R.id.seekBar)
        val Ocena_Bar = findViewById<TextView>(R.id.ocena_label)

        Seek_Bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                val ocena = progress + 1
                Ocena_Bar.setText("Ocena: $ocena")
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        // -------------------- DZIAŁANIE_RadioButton --------------------
        radio_Group.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            // Zmiana obrazka w zależności od wyboru
            when (checkedId) {
                R.id.radio_ksiazka -> { selectedImageResId = R.drawable.book }
                R.id.radio_film -> { selectedImageResId = R.drawable.movie }
            }

            Toast.makeText(this, "Wybrano: ${selectedRadioButton.text}", Toast.LENGTH_SHORT).show()
        }


        //-----------------------DZIAŁANIE_Button----------------------------
        addButton.setOnClickListener {


            val newItemT = newItemEditT.text.toString()
            val newItemD = newItemEditD.text.toString()
            val newItemR = newItemEditR.text.toString()
            val newItemS = Ocena_Bar.text.toString()

            if(newItemT.isNotEmpty() && newItemD.isNotEmpty() && newItemR.isNotEmpty()){
                items.add(MyListItem(selectedImageResId, newItemT,newItemD, newItemR, newItemS ))
                adapter.notifyDataSetChanged()

                    newItemEditT.text.clear()
                    newItemEditD.text.clear()
                    newItemEditR.text.clear()


                    Toast.makeText(this, "Dodano $newItemT", Toast.LENGTH_LONG).show()
            } else{
                    Toast.makeText(this, "Napisz Recenzje", Toast.LENGTH_LONG).show()
            }
        }
    }
}







