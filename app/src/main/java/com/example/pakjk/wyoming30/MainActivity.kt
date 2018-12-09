package com.example.pakjk.wyoming30

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var messageDTOs = arrayListOf<MessageDTO>()
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview.adapter = MyRecylclerViewAdapter(messageDTOs)
        recyclerview.layoutManager = LinearLayoutManager(this)

        button.setOnClickListener {
            if(!TextUtils.isEmpty(editText.text)){
                messageDTOs.add(MessageDTO(true,editText.text.toString()))
                (recyclerview.adapter as MyRecylclerViewAdapter).notifyDataSetChanged()
                recyclerview.smoothScrollToPosition(messageDTOs.size - 1)
                editText.setText("")
            }
        }
    }
}
