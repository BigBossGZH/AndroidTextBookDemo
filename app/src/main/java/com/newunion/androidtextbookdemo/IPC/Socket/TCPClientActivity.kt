package com.newunion.androidtextbookdemo.IPC.Socket

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import com.newunion.androidtextbookdemo.R
import kotlinx.android.synthetic.main.activity_tcpclient.*
import java.io.*
import java.net.Socket
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Administrator on 2018/2/27 0027.
 */
class TCPClientActivity : Activity(), View.OnClickListener {
    companion object {
        val MESSAGE_RECEIVE_NEW_MSG = 1
        val MESSAGE_SOCKET_CONNECTED = 2
    }

    lateinit var mPrintWriter: PrintWriter
    var mClientSocket: Socket?=null

    var mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MESSAGE_RECEIVE_NEW_MSG -> {
                    msg_container.setText(msg_container.text.toString() + msg.obj.toString())
                }
                MESSAGE_SOCKET_CONNECTED -> {
                    send.isEnabled = true
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tcpclient)
        send.setOnClickListener(this)
        var service = Intent(this, TCPServerService::class.java)
        startService(service)
        object : Thread() {
            override fun run() {
                conectTCPServer();
            }
        }.start()
    }

    override fun onDestroy() {
        mClientSocket?.let{
            try {
                it.shutdownInput()
                it.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        super.onDestroy()
    }

    override fun onClick(v: View?) {
        if (v == send) {
            val msgStr = msg.text.toString()
            if (!TextUtils.isEmpty(msgStr) && mPrintWriter != null) {
                mPrintWriter.println(msgStr)
                var time = formatDateTime(System.currentTimeMillis())
                val showedMsg = "self " + time + ":" + msgStr + "\n"
                msg_container.setText(msg_container.text.toString() + showedMsg)
            }
        }
    }

    private fun formatDateTime(time: Long): String {
        return SimpleDateFormat("(HH:mm:ss)").format(Date(time))
    }

    fun conectTCPServer() {
        var socket: Socket? = null
        while (socket == null) {
            try {
                socket = Socket("localhost", 8688)
                mClientSocket = socket
                mPrintWriter = PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
                mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                System.out.println("connect server success");
            } catch (e: IOException) {
                SystemClock.sleep(1000)
                System.out.println("connect tcp server failed,retry...")
            }
        }
        try {
            //接收服务器端的消息
            var br = BufferedReader(InputStreamReader(socket.getInputStream()))
            while (!this.isFinishing) {
                var msg = br.readLine()
                System.out.println("receive " + msg)
                if (msg != null) {
                    var time = formatDateTime(System.currentTimeMillis())
                    val showedMsg = "server " + time + ":" + msg + "\n"
                    mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showedMsg).sendToTarget()
                }
            }
            System.out.print("quit...")
            mPrintWriter.close()
            br.close()
            socket.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}