package com.newunion.androidtextbookdemo.IPC

import android.os.IInterface
import android.os.RemoteException

/**
 * Created by Administrator on 2018/1/30 0030.
 */
interface IBookManagerKt :IInterface{
    companion object {
        val DESCRIPTOR="com.newunion.androidtextbookdemo.IPC.IBookManager"
        val TRANSACTION_getBookList=android.os.IBinder.FIRST_CALL_TRANSACTION + 0
        val TRANSACTION_addBook=android.os.IBinder.FIRST_CALL_TRANSACTION + 1
    }
    @Throws(RemoteException::class)
    fun getBookList():List<Book>

    @Throws(RemoteException::class)
    fun addBook(book:Book)

}