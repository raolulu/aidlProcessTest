package com.example.myaidl.model;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;

public class TestModel {
    private Context mContext;
    private MutableLiveData<String> getText = new MutableLiveData<>();
    private MutableLiveData<String> sendText = new MutableLiveData<>();
    private static TestModel instance = null;
    public  TestModel(Context context){
        mContext = context;
        getText.setValue("");
    }

    public synchronized static TestModel getInstance(Context context){
        if(instance == null){
            instance = new TestModel(context);
        }
        return instance;
    }
    public MutableLiveData<String> getText() {
        return getText;
    }

    public MutableLiveData<String> getSendText(){return  sendText;}
}

