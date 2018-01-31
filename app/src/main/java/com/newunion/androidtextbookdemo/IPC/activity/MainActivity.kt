package com.newunion.androidtextbookdemo.IPC.activity

import android.content.Intent
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.newunion.androidtextbookdemo.IPC.MyConstants
import com.newunion.androidtextbookdemo.IPC.User
import com.newunion.androidtextbookdemo.R
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.ObjectOutputStream
//import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        persistToFile()

        Hello.setOnClickListener {
            var intent = Intent(this, ScecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun persistToFile() {
        Thread(Runnable {
            var user = User(1, "hello world", false)
            var dir = File(MyConstants.CHAPTER_2_PATH)
            if (!dir.exists()) {
                dir.mkdirs()
            }
            var objectException: ObjectOutputStream? = null
            var cachedFile = File(MyConstants.CACHE_FILE_PATH)
            try {
                objectException = ObjectOutputStream(FileOutputStream(cachedFile))
                objectException.writeObject(user)
                Log.e(TAG, "user  = " + user)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                objectException!!.close()


            }


        }).start()
    }

}


