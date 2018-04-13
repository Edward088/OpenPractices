package net.vsona.projecta;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by edward.ge on 2018/3/12.
 */

public class Book implements Parcelable {
    private String name;
    private int color;

    public Book(String name, int color) {
        this.name = name;
        this.color = color;
    }

    protected Book(Parcel in) {
        name = in.readString();
        color = in.readInt();
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
        color = in.readInt();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(color);
    }
}
