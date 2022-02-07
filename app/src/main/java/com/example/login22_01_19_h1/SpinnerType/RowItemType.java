package com.example.login22_01_19_h1.SpinnerType;

public class RowItemType {
    private String mCarTypeName;
    private int mCarTypeImage;

    public RowItemType(String mCarTypeName1) {
        mCarTypeName = mCarTypeName1;
        mCarTypeImage = mCarTypeImage;
    }

    public String getTypeName() {
        return mCarTypeName;
    }

    public int getTypeImage() {
        return mCarTypeImage;
    }
}