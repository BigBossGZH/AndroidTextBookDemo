package com.newunion.androidtextbookdemo.IPC.BinderPool

import android.util.Log

/**
 * Created by Administrator on 2018/2/27 0027.
 */
class SecurigyCenterImpl :ISecurityCenter.Stub(){
    companion object {
        var SECRET_CODE:Char='^'
    }
    override fun encrypt(content: String): String {
        var chars  = content.toCharArray()
        Log.e("SecurigyCenterImpl","SECRET_CODE = "+SECRET_CODE.toInt())
        for (i in 0 until chars.size) {
            chars[i] = (chars[i].toInt() xor SECRET_CODE.toInt()).toChar()
            Log.e("SecurigyCenterImpl","chars[i].toInt() = "+chars[i].toInt())
        }
        return String(chars)
    }

    override fun decrypt(password: String): String {
        return encrypt(password)
    }

}

class ComputeImpl :ICompute.Stub(){
    override fun add(a: Int, b: Int): Int {
        return a +b
    }

}
