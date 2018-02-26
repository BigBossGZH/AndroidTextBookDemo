package com.newunion.androidtextbookdemo.IPC.ContentPRovider

import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.newunion.androidtextbookdemo.IPC.Book
import com.newunion.androidtextbookdemo.IPC.User
import com.newunion.androidtextbookdemo.R

/**
 * Created by Administrator on 2018/2/26 0026.
 */
class ProviderActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider)
//        var uri = Uri.parse("content://com.newunion.androidtextbookdemo.IPC.ContentPRovider.BookProvider")
//        contentResolver.query(uri, null, null, null, null)
//        contentResolver.query(uri, null, null, null, null)
//        contentResolver.query(uri, null, null, null, null)

//        var bookUri = Uri.parse("content://com.newunion.androidtextbookdemo.IPC.ContentPRovider.BookProvider/book")
//        var values = ContentValues()
//        values.put("_id", 6)
//        values.put("name", "程序设计的艺术")
//        contentResolver.insert(bookUri, values)
//        var bookCursor = contentResolver.query(bookUri, arrayOf("_id","name"),null,null,null)
//
//        while (bookCursor.moveToNext()) {
//            var book = Book()
//            book.bookId = bookCursor.getInt(0)
//            book.bookName = bookCursor.getString(1)
//            Log.e("ProviderActivity","query book :"+Gson().toJson(book))
//        }
//        bookCursor.close()
//
//        var userUri = Uri.parse("content://com.newunion.androidtextbookdemo.IPC.ContentPRovider.BookProvider/user")
//        var userCursor = contentResolver.query(userUri, arrayOf("_id", "name", "sex"), null, null, null)
//        while (userCursor.moveToNext()) {
//            var user = User();
//            user.userId = userCursor.getInt(0)
//            user.userName = userCursor.getString(1)
//            user.isMale=userCursor.getInt(2)==1
//            Log.e("ProviderActivity","query user :"+Gson().toJson(user))
//        }
//        userCursor.close()
    }
}