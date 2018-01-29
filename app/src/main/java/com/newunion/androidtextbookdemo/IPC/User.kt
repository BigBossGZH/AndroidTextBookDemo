package com.newunion.androidtextbookdemo.IPC

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Administrator on 2018/1/29 0029.
 */
 class User constructor(var userId:Int =0,var userString:String,var isMale:Boolean):Parcelable{


    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(userId)
        dest.writeString(userString)
        dest.writeByte(if (isMale) 1 else 0)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}