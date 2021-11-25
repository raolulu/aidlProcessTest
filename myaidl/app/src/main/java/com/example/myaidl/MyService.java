package com.example.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.myaidl.viewmodel.TestViewModel;

public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();
    private TestViewModel testViewModel;
    private MyCallBack clientListener;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        testViewModel = new TestViewModel(getApplication());
        testViewModel.sendText().observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("lulutest","2222222");
                if (clientListener != null) {
                    Log.d("lulutest","2222222===");
                    ChatInfo chatInfo = new ChatInfo("66666666", "1111111");
                    try {
                        clientListener.onChange(chatInfo);
                        Log.d("lulutest","2222222==11111=");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private final IMyTestInterface.Stub IMyface =  new IMyTestInterface.Stub(){

        @Override
        public void sendMessage(ChatInfo chatInfo) {
            Log.d("lulutest","sendMessage!!!");
            Log.d("lulutest","message = " + chatInfo.getTimeContent() + "   " + chatInfo.getChatContent());
            testViewModel.getTestModelText().postValue("2222222" + "\n" + chatInfo.getChatContent());
        }

        @Override
        public void registListener(MyCallBack myCallBack){
            clientListener = myCallBack;
            Log.d("lulutest","callback init");
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
    //    throw new UnsupportedOperationException("Not yet implemented");
    return IMyface;
    }



}