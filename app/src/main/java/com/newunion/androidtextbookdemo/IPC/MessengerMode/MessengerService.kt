package com.newunion.androidtextbookdemo.IPC.MessengerMode

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.newunion.androidtextbookdemo.IPC.MyConstants

/**
 * Created by Administrator on 2018/1/31 0031.
 */
class MessengerService : Service() {

    companion object {
        val TAG = "MessengerService"
    }

    open class MessgerHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg!!.what) {
                MyConstants.MSG_FROM_CLIENT -> {
                    Log.i(TAG, "receive msg from Client: " + msg.data.get("msg"))
                    var client = msg.replyTo
                    var relpyMessage = Message.obtain(null,MyConstants.MSG_FROM_SERVICE)
                    var bundle=Bundle()
                    bundle.putString("reply","呖 ，你的消息我已经收到 ，稍后会回复你。")
                    relpyMessage.data =bundle
                    try {
                        client.send(relpyMessage)

                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    val mMessenger = Messenger(MessgerHandler())
    override fun onBind(intent: Intent?): IBinder {
        return mMessenger.binder
    }

}