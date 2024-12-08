package com.example.fitnesstracker

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
        MyListItem(R.drawable.walk,"5km","1h","300kalc","Poziom trudności: Łatwy"),
        MyListItem(R.drawable.gym,"----","2h","600kalc","Poziom trudności: Trudny")
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
        val addButton = findViewById<Button>(R.id.buttonZapiszDane)
        val myListView: ListView = findViewById(R.id.listView)
        val radio_aktywnosc = findViewById<RadioGroup>(R.id.radioGroupAktywnosc)
        val newItem_dystans = findViewById<EditText>(R.id.editTextDystans)
        val newItem_czasTrwania = findViewById<EditText>(R.id.editTextCzasTrwania)
        val newItem_kalorie = findViewById<EditText>(R.id.editTextKalorie)
        var selectedImageResId = R.drawable.walk

        //-----------------------DZIAŁANIE_Adaptera---------------------------
        val adapter = MyCustomAdapter(this, R.layout.custom_list_item, items)
        myListView.adapter = adapter

        //-----------------------DZIAŁANIE_Seek_Bar------------------------
        val Seek_Bar = findViewById<SeekBar>(R.id.seekBarPoziomTrudnosci)
        val Ocena_Bar = findViewById<TextView>(R.id.poziomTrudnosciLabel)
        Seek_Bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when (progress) {
                    0 -> Ocena_Bar.text = "Poziom trudności: Łatwy"
                    1 -> Ocena_Bar.text = "Poziom trudności: Średni"
                    2 -> Ocena_Bar.text = "Poziom trudności: Trudny"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        // -------------------- DZIAŁANIE_RadioButton --------------------
        radio_aktywnosc.setOnCheckedChangeListener { group, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)

            // Zmiana obrazka w zależności od wyboru
            when (checkedId) {
                R.id.radioTreningSilowy -> { selectedImageResId = R.drawable.gym }
                R.id.radioBieg -> { selectedImageResId = R.drawable.run}
                R.id.radioSpace -> { selectedImageResId = R.drawable.walk}
            }

            Toast.makeText(this, "Wybrano: ${selectedRadioButton.text}", Toast.LENGTH_SHORT).show()
        }


        //-----------------------DZIAŁANIE_Button----------------------------
        addButton.setOnClickListener {


            val newItemD= newItem_dystans.text.toString()
            val newItemC = newItem_czasTrwania.text.toString()
            val newItemK = newItem_kalorie.text.toString()
            val newItemS = Ocena_Bar.text.toString()

            if(newItemK.isNotEmpty() && newItemD.isNotEmpty() && newItemC.isNotEmpty()){
                items.add(MyListItem(selectedImageResId, newItemD,newItemC, newItemK, newItemS ))
                adapter.notifyDataSetChanged()

                newItem_dystans.text.clear()
                newItem_czasTrwania.text.clear()
                newItem_kalorie.text.clear()


                Toast.makeText(this, "Zapis treningu", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Dodaj dane", Toast.LENGTH_LONG).show()
            }
        }
    }
}


