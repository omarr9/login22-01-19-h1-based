package com.example.login22_01_19_h1.sliderhome;


import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class CardHelper {

    int image;
    String title;
    GradientDrawable color;

    public CardHelper(GradientDrawable color, int image, String title) {
        this.image = image;
        this.title = title;
        this.color = color;
    }

    public CardHelper() {
    }

    public CardHelper(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    //
    public String getTitle() {
        return title;
    }


    public Drawable getgradient() {
        return color;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setColor(GradientDrawable color) {
        this.color = color;
    }
}