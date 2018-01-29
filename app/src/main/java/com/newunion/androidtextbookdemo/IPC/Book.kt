package com.newunion.androidtextbookdemo.IPC

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Administrator on 2018/1/29 0029.
 */
class Book constructor() :Parcelable{
    lateinit var bookName: String
    var bookId =0
    constructor(parcel: Parcel) : this() {
    }
    constructor(bookId:Int,bookName:String) : this() {
        this.bookId = bookId
        this.bookName =bookName
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bookId)
        parcel.writeString(bookName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}