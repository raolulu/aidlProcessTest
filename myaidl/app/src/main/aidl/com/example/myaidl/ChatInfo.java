package com.example.myaidl;

import android.os.Parcel;
import android.os.Parcelable;

public class ChatInfo implements Parcelable {

    private String chatContent;
    private String timeContent;

    public ChatInfo(String param1,String param2){
        chatContent = param1;
        timeContent = param2;
    }

    public ChatInfo(){}
    public ChatInfo(Parcel in){
        chatContent = in.readString();
        timeContent = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(chatContent);
        dest.writeString(timeContent);
    }


    public static final Creator<ChatInfo>  CREATOR = new Creator<ChatInfo>(){

        @Override
        public ChatInfo createFromParcel(Parcel source) {
            return new ChatInfo(source);
        }

        @Override
        public ChatInfo[] newArray(int size) {
            return new ChatInfo[size];
        }
    };


    public void readFromParcel(Parcel dest){
        chatContent = dest.readString();
        timeContent = dest.readString();
    }
    public String getChatContent() {
        return chatContent;
    }

    public String getTimeContent() {
        return timeContent;
    }

    public String toString(){
        return "time:" + timeContent + "\n\t" + "text:" + chatContent;
    }
}
