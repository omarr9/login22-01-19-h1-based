package com.example.login22_01_19_h1.Menu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login22_01_19_h1.DBHelper;
import com.example.login22_01_19_h1.LoginSingup.Login;
import com.example.login22_01_19_h1.LoginSingup.SignUp;
import com.example.login22_01_19_h1.MainActivity;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.sliderhome.CardHelper;
import com.example.login22_01_19_h1.sliderhome.adapterCard;
import com.example.login22_01_19_h1.sliderhome.car;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements adapterCard.ListItemClickListener {

    @Nullable
    TextView carnameview;
    ImageView imgtypeview;
    DBHelper dbHelper;
    ArrayList<String> carname, cartype, carid;
    //CustomAdapter customAdapter;

    RecyclerView carRecycler;
    RecyclerView.Adapter adapter;



    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Hooks
        carRecycler = getView().findViewById(R.id.my_recycler);
        phoneRecycler();

    }
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    private void phoneRecycler() {

        //All Gradients
        GradientDrawable gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        GradientDrawable gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        GradientDrawable gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        //carRecycler.setHasFixedSize(true);
        //carRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CardHelper> cards = new ArrayList<>();



        cards.add(new CardHelper(gradient1, R.drawable.car, "Mazda"));
        cards.add(new CardHelper(gradient4, R.drawable.suvcar, "Sequoia"));
        cards.add(new CardHelper(gradient2, R.drawable.car, "Mazda 3"));


        //adapter = new adapterCard(cards,this);
        //carRecycler.setAdapter(adapter);


        ////////////
        imgtypeview=getView().findViewById(R.id.car_image);
//        carnameview=getView().findViewById(R.id.textNumber);

        dbHelper = new DBHelper(this.getActivity());
        boolean b =dbHelper.insetCarData("Sonata",1,3);
        carname = new ArrayList<>();
        cartype = new ArrayList<>();
        carid = new ArrayList<>();


        storeDataInArrays();
        System.out.println("test : "+cartype.size());

        for (int i =0 ; cartype.size()>i ; i++){
//            if((Integer.parseInt(cartype.get(i)))==0){
                cards.add(new CardHelper(gradient2, R.drawable.car, carname.get(i)));
            System.out.println(carname.get(i)+"/n");
//            }else {
//                cards.add(new CardHelper(gradient2, R.drawable.suvcar, carname.get(i)));
//            }
        }
        System.out.println("/n cards size "+cards.size());
        adapter = new adapterCard(cards, this);
        //adapter = new adapterCard(this.getActivity(), carname, cartype, carid ,this);
        carRecycler.setAdapter(adapter);
        carRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

    }
    void storeDataInArrays(){
        Cursor cursor = dbHelper.readcarsData();
        if(cursor.getCount() == 0){
            imgtypeview.setBackgroundResource(R.drawable.plus);
            carnameview.setText("Add Car");
            imgtypeview.setVisibility(View.VISIBLE);
            carnameview.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                carname.add(cursor.getString(0));
                System.out.printf("carname"+carname.get(0));
                cartype.add(cursor.getString(1));
                carid.add(cursor.getString(2));
            }
            //imgtypeview.setVisibility(View.VISIBLE);
            //carnameview.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onphoneListClick(int clickedItemIndex) {

    }


    /*@Override
    public void onphoneListClick(int clickedItemIndex) {

        Intent mIntent;
        switch (clickedItemIndex){
            case 0: //first item in Recycler view
                mIntent  = new Intent(SettingsFragment.this, car.class);
                startActivity(mIntent);
                break;

        }


    }*/
}