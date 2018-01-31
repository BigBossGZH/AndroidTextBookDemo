package com.newunion.androidtextbookdemo.IPC.MessengerMode

import android.app.Activity
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import com.newunion.androidtextbookdemo.IPC.MyConstants
import java.io.ObjectInputStream

/**
 * Created by Administrator on 2018/1/31 0031.
 */
class MessengerActivity : Activity() {
    companion object {
        val TAG = "MessengerActivity"
    }

    lateinit var mService: Messenger
    var mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mService = Messenger(service)
            var msg = Message.obtain(null, MyConstants.MSG_FROM_CLIENT)
            var data = Bundle()
            data.putString("msg","hello,this is client")
            msg.data=data
            msg.replyTo=mGetReplyMessenger
            try {
                mService.send(msg)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = Intent(this, MessengerService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    var mGetReplyMessenger = Messenger(MessengerHandler())

    open class MessengerHandler:Handler(){
        override fun handleMessage(msg: Message?) {
            when (msg!!.what) {
                MyConstants.MSG_FROM_SERVICE->{
                    Log.i(TAG, "receive msg from Service : " + msg.data.get("reply"))
                }
                else ->super.handleMessage(msg)
            }
            super.handleMessage(msg)

        }
    }

    override fun onDestroy() {
        unbindService(mConnection)
        super.onDestroy()
    }
}
