package com.example.login22_01_19_h1.SpinnerCar;

public class CarRowItem {
    private String mCompanyBrandName;
    private int mCarImage;

    public CarRowItem(String companyBrandName , int carImage) {
        mCompanyBrandName = companyBrandName;
        mCarImage = carImage;
    }

    public CarRowItem(String companyBrandName) {
        mCompanyBrandName = companyBrandName;
    }


    public String getCarName() {
        return mCompanyBrandName;
    }

    public int getBrandImage() {
        return mCarImage;
    }
}