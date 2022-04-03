package com.example.login22_01_19_h1.SpinnerCompany;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class RowItem {
    private String mCompanyID;
    private String mCompanyBrandName;
    private Bitmap mCompanyBrandImage;

    public RowItem(String CompanyID, String companyBrandName, String companyBrandImage) {
        this.mCompanyID = CompanyID;
        this.mCompanyBrandName = companyBrandName.toString();
        this.mCompanyBrandImage = StringToBitMap(companyBrandImage);
    }

    public String getCompanyName() {
        return mCompanyBrandName.toString();
    }

    public Bitmap getBrandImage() {
        return mCompanyBrandImage;
    }

    public String getmCompanyID(){
        return mCompanyID;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}