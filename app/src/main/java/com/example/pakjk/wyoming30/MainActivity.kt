package com.example.pakjk.wyoming30

import ai.api.AIConfiguration
import ai.api.AIDataService
import ai.api.model.AIRequest
import ai.api.model.Result
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var messageDTOs = arrayListOf<MessageDTO>()
    var aiDataService : AIDataService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview.adapter = MyRecylclerViewAdapter(messageDTOs)
        recyclerview.layoutManager = LinearLayoutManager(this)
        /**
         * console.dialogflow.com > V1 API > Developer access token copy
         * */
        var config = AIConfiguration("6a3213011c1d487a8e7fde861aaafe91", AIConfiguration.SupportedLanguages.Korean)
        aiDataService = AIDataService(config)
        button.setOnClickListener {
            if(!TextUtils.isEmpty(editText.text)){
                messageDTOs.add(MessageDTO(true,editText.text.toString()))
                (recyclerview.adapter as MyRecylclerViewAdapter).notifyDataSetChanged()
                recyclerview.smoothScrollToPosition(messageDTOs.size - 1)
                TalkAsyncTask().execute(editText.text.toString())  // step3
                          editText.setText("")
            }
        }
    } // onCreate End
    inner class TalkAsyncTask: AsyncTask<String,Void, Result>(){
        override fun doInBackground(vararg params: String?): Result? {
             //import ai.api.model.Result
            var aiRequest = AIRequest()
            aiRequest.setQuery(params[0]);
            return aiDataService?.request(aiRequest)?.result
        }

        override fun onPostExecute(result: Result?) {
            if(result != null){
                //println(result.fulfillment.speech)
                makeMessage(result)
            }
        }

    }
    fun makeMessage(result: Result){
        var speech = result.fulfillment.speech
        messageDTOs.add(MessageDTO(false,speech))
        recyclerview.adapter?.notifyDataSetChanged() // 원본에는 ? 이 없음.
        recyclerview.smoothScrollToPosition(messageDTOs.size-1)
    }
}
