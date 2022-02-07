package com.example.login22_01_19_h1.SpinnerCompany;

public class RowItem {
    private String mCompanyBrandName;
    private int mCompanyBrandImage;

    public RowItem(String companyBrandName, int companyBrandImage) {
        mCompanyBrandName = companyBrandName.toString();
        mCompanyBrandImage = companyBrandImage;
    }

    public String getCompanyName() {
        return mCompanyBrandName.toString();
    }

    public int getBrandImage() {
        return mCompanyBrandImage;
    }
}