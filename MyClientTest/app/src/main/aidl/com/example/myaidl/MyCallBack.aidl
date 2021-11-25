// MyCallBack.aidl
package com.example.myaidl;

// Declare any non-default types here with import statements
import com.example.myaidl.ChatInfo;
interface MyCallBack {
    void onChange(in ChatInfo chatInfo);
}