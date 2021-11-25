package com.example.myaidl.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myaidl.model.TestModel;

public class TestViewModel extends ViewModel {
    private TestModel testModel;
    public TestViewModel(Application application){
        testModel = TestModel.getInstance(application);
    }
    public MutableLiveData<String> getTestModelText(){
        return testModel.getText();
    }

    public MutableLiveData<String> sendText(){
        return  testModel.getSendText();
    }
}
