package com.newunion.androidtextbookdemo.IPC.BinderPool;

import android.os.RemoteException;

/**
 * Created by Administrator on 2018/2/27 0027.
 */

public class SecurigyCenterImplJava extends ISecurityCenter.Stub{
    final char SECRET_CODE = '^';

    @Override
    public String encrypt(String content) throws RemoteException {
        char[] chars = content.toCharArray();
        for (int i =0;i<chars.length;i++) {
            chars[i]^=SECRET_CODE;
        }
        return new String(chars);
    }

    @Override
    public String decrypt(String password) throws RemoteException {
        return encrypt(password);
    }
}
