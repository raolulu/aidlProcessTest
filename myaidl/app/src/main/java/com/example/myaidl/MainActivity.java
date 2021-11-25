package com.example.myaidl;

import android.app.Application;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.myaidl.databinding.ActivityMainBinding;
import com.example.myaidl.viewmodel.TestViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding mainBinding;
    private TestViewModel testViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.inflate(LayoutInflater.from(this),R.layout.activity_main,null,false);
//        testViewModel = new ViewModelProvider(this,new TestViewModelFactory(getApplication())).get(TestViewModel.class);
        testViewModel = new TestViewModel(getApplication());
        mainBinding.setVm(testViewModel);
        mainBinding.setLifecycleOwner(this);
        setContentView(mainBinding.getRoot());
        mainBinding.chatContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        testViewModel.getTestModelText().observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                setChontent(s);
            }
        });
    }

    public void sendOnClick(View view){
        Log.d("lulutest","send click");
        if(testViewModel == null){
            Log.d("lulutest","nnull");
            return;
        }
        testViewModel.sendText().setValue(mainBinding.messageText.getText().toString());
        setChontent("11111111\n" +mainBinding.messageText.getText().toString());
        mainBinding.messageText.setText("");
    }

    private void setChontent(String s){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mainBinding.chatContent.getText()+ "\n");
        stringBuilder.append(s);
        mainBinding.chatContent.setText(stringBuilder.toString());
    }
}