package com.example.pakjk.wyoming30

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_comment.view.*

data class MessageDTO(var myMessage:Boolean? = null, var message:String? = null)

class MyRecylclerViewAdapter(messageDTOs: ArrayList<MessageDTO>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var messageDTOs = messageDTOs
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CustomViewHolder(view)
    }

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(): Int {
        return messageDTOs.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(messageDTOs[position].myMessage !!){
            holder.itemView.right_textView.visibility = View.VISIBLE
            holder.itemView.right_textView.text = messageDTOs[position].message
            holder.itemView.left_textView.visibility = View.INVISIBLE
        }else{
            holder.itemView.left_textView.visibility = View.VISIBLE
            holder.itemView.left_textView.text = messageDTOs[position].message
            holder.itemView.right_textView.visibility = View.INVISIBLE
        }
    }
}


