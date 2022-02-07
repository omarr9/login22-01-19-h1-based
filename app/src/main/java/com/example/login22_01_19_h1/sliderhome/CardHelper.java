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

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }


    public Drawable getgradient() {
        return color;
    }


}