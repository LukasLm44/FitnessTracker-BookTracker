package com.example.moviebooktracker

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



        val icon: ImageView = view.findViewById(R.id.itemIcon_imageBook)
        val textTitle: TextView = view.findViewById(R.id.titleTextView)
        val textDescription: TextView = view.findViewById(R.id.descriptionTextView)
        val textReview: TextView = view.findViewById(R.id.my_reviewTextView)
        val textRate: TextView = view.findViewById(R.id.RateTextView)


        val item = items[position]

        icon.setImageResource(item.iconResId)
        textTitle.text = item.title
        textDescription.text = item.description
        textReview.text = item.review
        textRate.text = item.rate


        return view

    }

}
