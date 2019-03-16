package com.example.payfazzmobilechallange;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopNews implements Comparable<TopNews>, Parcelable {

    @Expose
    @SerializedName("by")
    private String author;

    @Expose
    @SerializedName("descendants")
    private int descendants;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("kids")
    private List<Integer> kids;

    @Expose
    @SerializedName("score")
    private int name;

    @Expose
    @SerializedName("title")
    private String title;

    @Expose
    @SerializedName("time")
    private int time;

    @Expose
    @SerializedName("type")
    private String type;

    protected TopNews(Parcel in) {
        author = in.readString();
        descendants = in.readInt();
        id = in.readInt();
        name = in.readInt();
        title = in.readString();
        time = in.readInt();
        type = in.readString();
        date = in.readString();
        clicked = in.readInt();
        url = in.readString();
    }

    public static final Creator<TopNews> CREATOR = new Creator<TopNews>() {
        @Override
        public TopNews createFromParcel(Parcel in) {
            return new TopNews(in);
        }

        @Override
        public TopNews[] newArray(int size) {
            return new TopNews[size];
        }
    };

    public String getAuthor() {
        return author;
    }

    private String date;


    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getKids() {
        return kids;
    }

    public void setKids(List<Integer> kids) {
        this.kids = kids;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int clicked =0;


    @Expose
    @SerializedName("url")
    private String url;

    @Override
    public int compareTo(@NonNull TopNews o) {
        int compareTime = o.getTime();

        return this.time-compareTime;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeInt(descendants);
        dest.writeInt(id);
        dest.writeInt(name);
        dest.writeString(title);
        dest.writeInt(time);
        dest.writeString(type);
        dest.writeString(date);
        dest.writeInt(clicked);
        dest.writeString(url);
    }
}
