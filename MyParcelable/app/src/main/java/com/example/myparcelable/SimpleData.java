package com.example.myparcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    int num;
    String message;

    public SimpleData(int num, String message) {
        this.num = num;
        this.message = message;
    }
    public SimpleData(Parcel src){
         num = src.readInt();
         message = src.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public SimpleData createFromParcel(Parcel src) {
            return new SimpleData(src);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
          dest.writeInt(num);
          dest.writeString(message);
    }
}
