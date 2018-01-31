package com.newunion.androidtextbookdemo.IPC.activity

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.newunion.androidtextbookdemo.IPC.MyConstants
import com.newunion.androidtextbookdemo.IPC.User
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInput
import java.io.ObjectInputStream

/**
 * Created by Administrator on 2018/1/30 0030.
 */
class ScecondActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var user:User
        var cachedFile = File(MyConstants.CACHE_FILE_PATH)
        if (cachedFile.exists()) {
            var objectInputStream = ObjectInputStream(FileInputStream(cachedFile))
            user = objectInputStream.readObject() as User;

            Log.e("ScecondActivity", "  user = " + user)
            objectInputStream.close()
        }
    }
}