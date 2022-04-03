package com.example.login22_01_19_h1.sliderhome;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class CardHelper {

    int image;
    String title;
    String company;
    int id;

    public CardHelper( int image,String company, String title, int id) {
        this.image = image;
        this.title = title;
        this.company = company;
        this.id = id;
    }

    public CardHelper() {
    }

    public CardHelper(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
    public String getCompany() {
        return company;
    }
    public Integer getid() {
        return id;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}