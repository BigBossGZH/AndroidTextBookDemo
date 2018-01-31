package com.newunion.androidtextbookdemo.IPC.AIDLMode

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.google.gson.Gson
import com.newunion.androidtextbookdemo.IPC.Book
import com.newunion.androidtextbookdemo.IPC.IBookManager

/**
 * Created by Administrator on 2018/1/31 0031.
 */
class BookManagerActivity :Activity(){
    companion object {
        val TAG="BookManagerActivity"
    }
    private val mConnection =object :ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val bookManager = IBookManager.Stub.asInterface(service)
            try {
                var list = bookManager.bookList
//                Log.i(TAG, "query book list ,list type : " + list.javaClass.canonicalName)
                Log.i(TAG, "query book list : " +Gson().toJson(list) )
                var newBook = Book(3,"Android 开发技术探究")
                bookManager.addBook(newBook)
                Log.i(TAG, "add book : " +Gson().toJson(newBook) )
                var newList = bookManager.bookList
                Log.i(TAG, "query book list : " +Gson().toJson(newList) )
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var intent = Intent(this, BookManagerService::class.java)
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onDestroy() {
        unbindService(mConnection)
        super.onDestroy()
    }
}