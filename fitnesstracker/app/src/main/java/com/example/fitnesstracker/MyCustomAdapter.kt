package com.example.fitnesstracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView


class MyCustomAdapter(context: Context,

                      private val resource: Int,
                      private val items: List<MyListItem>) :

    ArrayAdapter<MyListItem>(context, resource, items) {



    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = convertView ?: LayoutInflater.from(context).inflate(resource, parent, false)



        val icon: ImageView = view.findViewById(R.id.itemIcon_imageWalk)
        val textdystans: TextView = view.findViewById(R.id.dystans)
        val textCzasT: TextView = view.findViewById(R.id.czasT)
        val textKalorie: TextView = view.findViewById(R.id.kalorie)
        val textPoziom: TextView = view.findViewById(R.id.poziom)


        val item = items[position]

        icon.setImageResource(item.iconResId)
        textdystans.text = item.dystans
        textCzasT.text = item.czas
        textKalorie.text = item.kalorie
        textPoziom.text = item.poziom


        return view

    }

}