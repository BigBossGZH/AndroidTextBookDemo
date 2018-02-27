package com.newunion.androidtextbookdemo.IPC.BinderPool

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Created by Administrator on 2018/2/27 0027.
 */
class BinderPoolService : Service(){
    companion object {
        val TAG="BinderPoolService"
    }
    private var mBindPool=IBinderPool

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG,"onBind")
        return mBindPool
    }

}