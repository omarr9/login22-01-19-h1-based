package com.example.login22_01_19_h1.SpinnerCar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class CarRowItem {
    private String OurCarID;
    private String mCompanyID;
    private String CarType;
    private String CarName;
    private Bitmap mCarImage;

    public CarRowItem(String ourCarID, String companyBrandName , String cartype, String carname, String carImage) {
        this.OurCarID = ourCarID;
        this.mCompanyID = companyBrandName;
        this.CarType = cartype;
        this.CarName = carname;
        this.mCarImage = StringToBitMap(carImage);
    }

    public CarRowItem(String companyBrandName) {
        mCompanyID = companyBrandName;
    }


    public String getmCompanyID() {
        return mCompanyID;
    }
    public String getCarType() {
        return CarType;
    }
    public String getCarName() {
        return CarName;
    }
    public String getOurCarID() {
        return OurCarID;
    }

    public Bitmap getBrandImage() {
        return mCarImage;
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