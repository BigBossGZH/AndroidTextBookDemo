package com.newunion.androidtextbookdemo.IPC.ContentPRovider

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by Administrator on 2018/2/26 0026.
 */
class DbOpenHelper : SQLiteOpenHelper {

    constructor(context: Context) : super(context, DB_NAME, null, DB_VERSION) {

    }

    companion object {
        val DB_NAME = "book_provider.db"
        val BOOK_TABLE_NAME = "book"
        val USER_TABLE_NAME = "user"
        val DB_VERSION = 1
    }


    //图书和用户信息表
    var CREATE_BOOK_TABLE = "CREATE TABLE IF NOT EXISTS " +
            BOOK_TABLE_NAME + "(_id INTEGER PRIMARY KEY," + "name TEXT)"

    var CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " +
            USER_TABLE_NAME + "(_id INTEGER PRIMARY KEY," + "name TEXT," + "sex INT)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_BOOK_TABLE)
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}