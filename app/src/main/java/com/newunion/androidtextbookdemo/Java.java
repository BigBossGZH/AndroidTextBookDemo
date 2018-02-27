package com.newunion.androidtextbookdemo;

import android.content.Intent;
import android.os.Binder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

import com.newunion.androidtextbookdemo.IPC.Socket.TCPServerService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2018/1/29 0029.
 */

class Book extends Binder implements Parcelable {

    public int bookId;
    public String bookName;
    CopyOnWriteArrayList<String> dd =  new CopyOnWriteArrayList<String>();

    protected Book(Parcel in) {
        new Thread(){
            @Override
            public void run() {
                super.run();
            }
        }.start();

    }

    ObjectOutputStream objectOutputStream;

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        if (mRemoteBookManager!=null&&mRemoteBookManager.asBinder().isBinderAlive) {
//            try {
//                mRemoteBookManager.unregisterListener(mOnNewBookArrivedListener)
//            } catch (e: RemoteException) {
//                e.printStackTrace()
//            }
//
//        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
