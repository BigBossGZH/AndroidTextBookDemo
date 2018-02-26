package com.newunion.androidtextbookdemo.IPC.ContentPRovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import java.lang.IllegalArgumentException
import java.net.URI

/**
 * Created by Administrator on 2018/2/8 0008.
 */
class BookProvider : ContentProvider() {
    companion object {
        val TAG = "BookProvider"
        val AUTHORITY = "com.newunion.androidtextbookdemo.IPC.ContentPRovider.BookProvider"
        val BOOK_CONTENT_URI: Uri = Uri.parse("content://" + AUTHORITY + "/book")
        val USER_CONTENT_URI: Uri = Uri.parse("content://" + AUTHORITY + "/user")
        val BOOK_URI_CODE = 0
        val USER_URI_CODE = 1
        val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            sUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE)
            sUriMatcher.addURI(AUTHORITY, "user", USER_URI_CODE)
        }
    }
    lateinit var mContext: Context
    lateinit var mDb :SQLiteDatabase

    fun getTableName(uri:Uri):String?{
        var tableName:String ?=null
        when (sUriMatcher.match(uri)) {
            BOOK_URI_CODE -> tableName = DbOpenHelper.BOOK_TABLE_NAME
            USER_URI_CODE -> tableName = DbOpenHelper.USER_TABLE_NAME
        }
        return tableName
    }

    override fun onCreate(): Boolean {
        Log.d(TAG, "oncreate,current thread: " + Thread.currentThread().name)
        mContext= context;
        initProviderData();
        return false
    }

    private fun initProviderData() {
        mDb = DbOpenHelper(mContext).writableDatabase
        mDb.execSQL("delete from "+DbOpenHelper.BOOK_TABLE_NAME)
        mDb.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME)
        mDb.execSQL("insert into book values(3,'Android');")
        mDb.execSQL("insert into book values(4,'Ios');")
        mDb.execSQL("insert into book values(5,'Html5');")
        mDb.execSQL("insert into user values(1,'jake',1);")
        mDb.execSQL("insert into user values(2,'jasmine',0);")

    }

    override fun query(uri: Uri, projection: Array<out String>?,
                       selection: String?,
                       selectionArgs: Array<out String>?,
                       sortOrder: String?): Cursor? {

        Log.d(TAG, "query,current thread: " + Thread.currentThread().name)
        var table = getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI: "+uri)
        }
        return mDb.query(table,projection,selection,selectionArgs,null,null,sortOrder,null)
    }

    override fun getType(uri: Uri?): String? {
        Log.d(TAG, "getType")
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        Log.d(TAG, "insert")
        var table =getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI: "+uri)
        }
        mDb.insert(table, null, values)
        mContext.contentResolver.notifyChange(uri,null)
        return uri
    }

    override fun delete(uri: Uri, selection: String, selectionArgs: Array<out String>): Int {
        Log.d(TAG, "insert")
        var table =getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI: "+uri)
        }
        var count = mDb.delete(table,selection,selectionArgs)
        if (count > 0) {
            context.contentResolver.notifyChange(uri,null)
        }
        return count
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d(TAG, "insert")
        var table =getTableName(uri)
        if (table == null) {
            throw IllegalArgumentException("Unsupported URI: "+uri)
        }
        var row = mDb.update(table,values,selection,selectionArgs)
        if (row > 0) {
            context.contentResolver.notifyChange(uri,null)
        }
        return row
    }


}