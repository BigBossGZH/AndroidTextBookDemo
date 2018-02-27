package com.newunion.androidtextbookdemo.IPC.BinderPool

import android.content.Context
import java.util.concurrent.CountDownLatch

/**
 * Created by Administrator on 2018/2/27 0027.
 */
class BinderPool{
    companion object {
        val TAG = "BinderPool"
        val BINDER_NONE=-1
        val BINDER_COMPUTE=0
        val BINDER_SECURITY_CENTER=1
        @Volatile
        lateinit var  sInstance:BinderPool
    }

    lateinit var mContext: Context
    lateinit var mBinderPool: IBinderPool
    lateinit var mConnectBinderPoolCountDownLatch: CountDownLatch

}