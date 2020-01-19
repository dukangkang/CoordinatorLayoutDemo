package com.docking.coordinatorlayout.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomEntity implements Parcelable {
    public String title;
    public String url;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
    }

    public CustomEntity() {
    }

    protected CustomEntity(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<CustomEntity> CREATOR = new Parcelable.Creator<CustomEntity>() {
        @Override
        public CustomEntity createFromParcel(Parcel source) {
            return new CustomEntity(source);
        }

        @Override
        public CustomEntity[] newArray(int size) {
            return new CustomEntity[size];
        }
    };

    @Override
    public String toString() {
        return "CustomEntity{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
