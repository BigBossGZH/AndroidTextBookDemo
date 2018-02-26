package com.newunion.androidtextbookdemo.IPC.Socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Parcel
import java.io.*
import java.net.ServerSocket
import java.net.Socket

/**
 * Created by Administrator on 2018/2/26 0026.
 */
var mISServiceDestoryed = false

class TCPServerService : Service() {

    var mDefindeMessages = arrayOf("你好啊，哈哈",
            "请问你叫什么名字呀？",
            "今天北京天气不错啊，shy",
            "你知道吗？我可是可以和多个人同时聊天的哦",
            "给你讲个笑话吧：据说爱笑的人运气不会太差，不知道真假")

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        Thread(TcpServer()).start()
        super.onCreate()
    }

    override fun onDestroy() {
        mISServiceDestoryed = true
        super.onDestroy()
    }
}

class TcpServer : Runnable {
    override fun run() {
        var serverSocket: ServerSocket? = null
        try {
            serverSocket = ServerSocket(8688)
        } catch (e: IOException) {
            System.err.println("establish tcp server failed,prot:8688")
            e.printStackTrace()
            return
        }
        while (!mISServiceDestoryed) {
            try {
                val client = serverSocket.accept()
                System.out.println("accept")
                Thread() {
                    try {
//                        resp
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    @Throws(IOException::class)
    fun responseClient(client: Socket) {
        var bufferedReader = BufferedReader(InputStreamReader(client.getInputStream()))
        var out = PrintWriter(BufferedWriter(OutputStreamWriter(client.getOutputStream())), true)
        out.println("欢迎来到聊天室")
        while (!mISServiceDestoryed) {
            var str = bufferedReader.readLine()
            System.out.println("msg from client: "+str)
            if (str == null) {
                //客户端断开连接
                break;
            }

        }
    }

}
