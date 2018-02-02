package com.newunion.androidtextbookdemo.IPC.AIDLMode

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import com.google.gson.Gson
import com.newunion.androidtextbookdemo.IPC.Book
import com.newunion.androidtextbookdemo.IPC.IBookManager
import com.newunion.androidtextbookdemo.IPC.IOnNewBookArrivedListener
import com.newunion.androidtextbookdemo.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Administrator on 2018/1/31 0031.
 */
class BookManagerActivity : Activity() {
    companion object {
        val TAG = "BookManagerActivity"
        val MESSAGE_NEW_BOOK_ARRIVED = 1
    }

    var mRemoteBookManager: IBookManager? = null

    var mHandler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg!!.what) {
                MESSAGE_NEW_BOOK_ARRIVED -> Log.d(TAG, "receive new book :" + Gson().toJson(msg.obj))
                else -> super.handleMessage(msg)
            }
        }
    }

    private val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val bookManager = IBookManager.Stub.asInterface(service)
            try {
                mRemoteBookManager = bookManager
                var list = bookManager.bookList
                Log.i(TAG, "query book list ,list type : " + list.javaClass.canonicalName)
                Log.i(TAG, "query book list : " + Gson().toJson(list))
                var newBook = Book(3, "Android 开发技术探究")
                bookManager.addBook(newBook)
                Log.i(TAG, "add book : " + Gson().toJson(newBook))
                var newList = bookManager.bookList
                Log.i(TAG, "query book list : " + Gson().toJson(newList))
                bookManager.registerListener(mOnNewBookArrivedListener)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mRemoteBookManager = null

        }
    }
    var mOnNewBookArrivedListener = object : IOnNewBookArrivedListener.Stub() {
        override fun onNewBookArrived(newBook: Book?) {
            mHandler.obtainMessage(MESSAGE_NEW_BOOK_ARRIVED, newBook).sendToTarget()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var intent = Intent(this, BookManagerService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        Hello.setOnClickListener {
            var newList = mRemoteBookManager?.bookList
            Log.e(TAG, newList.toString())

        }
    }

    override fun onDestroy() {
        mRemoteBookManager?.let {
            if (it.asBinder().isBinderAlive) {
                try {
                    Log.i(TAG, "unregister listener : " + mOnNewBookArrivedListener)
                    it.unregisterListener(mOnNewBookArrivedListener)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
        unbindService(mConnection)
        super.onDestroy()
    }
}