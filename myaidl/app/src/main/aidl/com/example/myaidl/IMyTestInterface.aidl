// IMyTestInterface.aidl
package com.example.myaidl;

// Declare any non-default types here with import statements
import com.example.myaidl.ChatInfo;
import com.example.myaidl.MyCallBack;
interface IMyTestInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void sendMessage(in ChatInfo chatInfo);
    void registListener(in MyCallBack myCallBack);
}