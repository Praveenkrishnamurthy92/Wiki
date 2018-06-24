package com.moneytap.praveen.wiki.view

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.moneytap.praveen.wiki.R
import com.moneytap.praveen.wiki.model.WikiPojo

/**
 * Created by cheluva on 24/06/18.
 */
public class RecyViewAdapter : RecyclerView.Adapter<RecyViewAdapter.MyHolder> {

    var context: Context
    var list: ArrayList<WikiPojo.WikiItem>
    var inflater: LayoutInflater

    constructor(context: Context, list: ArrayList<WikiPojo.WikiItem>) {
        this.context = context
        this.list = list
        inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.title.text = list.get(position).title
        holder.description.text = if (list.get(position).terms?.description?.size == 0) "" else list.get(position).terms?.description?.get(0)
        Glide.with(context)
                .load(if (list.get(position).thumbnail?.source == null) "https://image.flaticon.com/icons/png/32/33/33949.png" else list.get(position).thumbnail?.source)
                .into(holder.thumbnail_img)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view = inflater.inflate(R.layout.snippet_recycl_view_item, parent, false)
        return MyHolder(view, context)
    }

    class MyHolder : RecyclerView.ViewHolder, View.OnClickListener {

        lateinit var thumbnail_img: ImageView
        lateinit var title: TextView
        lateinit var description: TextView
        lateinit var context: Context

        constructor(itemView: View,context : Context) : super(itemView) {
            this.context = context
            thumbnail_img = itemView.findViewById(R.id.thumbnail_img)
            title = itemView.findViewById(R.id.title)
            description = itemView.findViewById(R.id.description)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var intent:Intent = Intent(context, WebActivity::class.java)
            intent.putExtra("title",title.text)
            context.startActivity(intent)
        }
    }
}