//package com.newunion.androidtextbookdemo.IPC
//
//import android.os.Binder
//import android.os.IBinder
//import android.os.Parcel
//import com.newunion.androidtextbookdemo.IPC.IBookManagerKt.Companion.DESCRIPTOR
//
///**
// * Created by Administrator on 2018/1/30 0030.
// */
//open class BookManagerImpl :Binder,IBookManagerKt {
//
//    constructor(){
//        this.attachInterface(this,DESCRIPTOR)
//    }
//
//    companion object {
//        fun asInterface(obj :IBinder):IBookManagerKt?{
//            if (obj==null)
//                return null;
//            var  iin  =obj.queryLocalInterface(DESCRIPTOR)
//            if (iin!=null&&iin is IBookManagerKt){
//                return iin
//            }
//            return BookManagerImpl.
//        }
//    }
//
//    override fun onTransact(code: Int, data: Parcel?, reply: Parcel?, flags: Int): Boolean {
//        return super.onTransact(code, data, reply, flags)
//    }
//
//    override fun getBookList(): List<Book> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun addBook(book: Book) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun asBinder(): IBinder {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    var mDeathRecipient = object :IBinder.DeathRecipient{
//        override fun binderDied() {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
//
//    }
//
//}
