package com.example.login22_01_19_h1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

//    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context) {
        super(context,"UserData",null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table UserDetails(userID TEXT primary key,name TEXT,password PASSWORD,number NUMBER)");
        DB.execSQL("create Table cars(carname TEXT,cartype NUMBER,carid NUMBER primary key)");
        DB.execSQL("create Table Test(carName TEXT,carType NUMBER,carCompany TEXT,carid NUMBER primary key)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists UserDetails");
        DB.execSQL("drop Table if exists cars");
        DB.execSQL("drop Table if exists Test");
    }
    public Boolean insetUserData(String name,String number,String email,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userID",email);
        contentValues.put("name",name);
        contentValues.put("password",password);
        contentValues.put("number",number);
        long result= DB.insert("UserDetails",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean insetCarData(String carname,int type,int id){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("carName",carname);
        contentValues.put("cartype",type);
        contentValues.put("carid",id);
        long result= DB.insert("cars",null,contentValues);
        if (result == -1){
            return false;
        }else {

            return true;
        }
    }

    public Boolean insetCarDataTest(String carname,String  cartype,String carCompany,int id ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("carname",carname);
        contentValues.put("carType",cartype);
        contentValues.put("carCompany",carCompany);
        contentValues.put("carid",id);
        long result= DB.insert("Test",null,contentValues);
        if (result == -1){
            return false;
        }else {

            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails ",null);
        return cursor;
    }

    public Cursor readcarsData(){
//        String query = "SELECT * FROM " + "cars";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor1 = null;
        if(db != null){
            cursor1 = db.rawQuery("SELECT * FROM cars ", null);
        }
        return cursor1;
    }

    public Cursor readcarsDataTest(){
//        String query = "SELECT * FROM " + "cars";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor2 = null;
        if(db != null){
            cursor2 = db.rawQuery("SELECT * FROM Test ", null);
        }
        return cursor2;
    }
}