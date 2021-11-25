package com.example.myclienttest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.myaidl.ChatInfo;
import com.example.myaidl.IMyTestInterface;
import com.example.myaidl.MyCallBack;

public class MainActivity extends AppCompatActivity{
    private IMyTestInterface myTestInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindServiceMy();
    }

    private MyCallBack myCallBack = new MyCallBack.Stub() {
        @Override
        public void onChange(ChatInfo chatInfo) throws RemoteException {
            Log.d("lulutest","listener == " + chatInfo.getChatContent() + "  " + chatInfo.getTimeContent());
        }
    };
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("lulutest","connect success!");
            myTestInterface = IMyTestInterface.Stub.asInterface(service);
            if(myTestInterface != null){
                try {
                    myTestInterface.registListener(myCallBack);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("lulutest","connect failed");
        }
    };
    public void sendOnClick(View view){
        ChatInfo chatInfo = new ChatInfo("wwwwwwwwww","2021-11-24");
        try {
            myTestInterface.sendMessage(chatInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void bindServiceMy(){
        Intent intent = new Intent("com.example.myaidl.MyService");
        intent.setPackage("com.example.myaidl");
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}