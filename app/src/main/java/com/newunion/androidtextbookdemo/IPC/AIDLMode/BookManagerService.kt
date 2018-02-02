package com.newunion.androidtextbookdemo.IPC.AIDLMode

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import com.newunion.androidtextbookdemo.IPC.Book
import com.newunion.androidtextbookdemo.IPC.IBookManager
import com.newunion.androidtextbookdemo.IPC.IOnNewBookArrivedListener
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by Administrator on 2018/1/31 0031.
 */
private var mIsServiceDestoryed = AtomicBoolean(false)
var mBookList = CopyOnWriteArrayList<Book>()
var mListenerList = RemoteCallbackList<IOnNewBookArrivedListener>()

class BookManagerService : Service() {
    companion object {
        val TAG = "BMS"
    }

    var mBinder = object : IBookManager.Stub() {


        override fun getBookList(): MutableList<Book> {
            SystemClock.sleep(5000)
            return mBookList
        }

        override fun addBook(book: Book?) {
            mBookList.add(book)
        }

        override fun registerListener(listener: IOnNewBookArrivedListener?) {
            mListenerList.register(listener)
//            if (!mListenerList.contains(listener)) {
//                mListenerList.add(listener)
//            } else {
//                Log.d(TAG, "already exists.")
//            }

            val  N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener,current size:" + N )
        }

        override fun unregisterListener(listener: IOnNewBookArrivedListener?) {
            mListenerList.unregister(listener)
//            if (mListenerList.contains(listener)) {
//                mListenerList.remove(listener)
//                Log.d(TAG, "unregister listener succeed.")
//            } else {
//                Log.d(TAG, "not found,can not unregister")
//            }
            val  N = mListenerList.beginBroadcast();
            mListenerList.finishBroadcast();
            Log.d(TAG, "unregisterListener,current size:" +N)
        }
    }

    override fun onCreate() {
        super.onCreate()
        mBookList.add(Book(1, "Android"))
        mBookList.add(Book(2, "ios"))
        Thread(ServiceWorker()).start()
    }

    override fun onDestroy() {
        mIsServiceDestoryed.set(true)
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }


    class ServiceWorker : Runnable {
        override fun run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                var bookId = mBookList.size + 1
                var newBook = Book(bookId, "new book#" + bookId)
                try {
                    onNewBookArrived(newBook)
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            }
        }
    }


}

@Throws(RemoteException::class)
fun onNewBookArrived(book: Book) {
    mBookList.add(book)
//    Log.d(BookManagerService.TAG, "onNewBookArrived,notify listeners:" + mListenerList.registeredCallbackCount)
//    for (i in 0 until mListenerList.size) {
//        var listener = mListenerList.get(i)
//        Log.d(BookManagerService.TAG, "onNewBookArrived,notify listeners:" + listener)
//        listener.onNewBookArrived(book)
//    }
    for (i in 0 until mListenerList.beginBroadcast()){
        var i = mListenerList.getBroadcastItem(i)
        i?.let {
            try {
                i.onNewBookArrived(book)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        mListenerList.finishBroadcast()
    }
}