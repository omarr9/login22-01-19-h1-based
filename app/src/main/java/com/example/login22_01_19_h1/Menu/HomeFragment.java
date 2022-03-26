package com.example.login22_01_19_h1.Menu;

//import android.app.Fragment;

import android.app.ProgressDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.DBHelper;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.sliderhome.CardHelper;
import com.example.login22_01_19_h1.sliderhome.adapterCard;

import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private ProgressDialog progressDialog;
    ArrayList<CardHelper> cards = new ArrayList<>();
    GradientDrawable gradient1;
    GradientDrawable gradient2;
    GradientDrawable gradient3;
    GradientDrawable gradient4;


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Hooks
        carRecycler = getView().findViewById(R.id.my_recycler);
        progressDialog = new ProgressDialog(getActivity());

        phoneRecycler();

    }
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home, container, false);

    }

    private void phoneRecycler() {

        //All Gradients
        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        cards.clear();

        cards.add(new CardHelper("Mazda"));


        ////////////
        imgtypeview = getView().findViewById(R.id.car_image);

        carname = new ArrayList<>();
        cartype = new ArrayList<>();


        System.out.println("test : " + cartype.size());


        ArrayList<String> myString = new ArrayList<>();
        String[] StrArr = new String[6];


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_CARS_RETRIVE, new Response.Listener<String>() {

            @Override
            public void onResponse(String json_re) {
                progressDialog.dismiss();


                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(json_re);
                    JSONObject jsonResponse = jsonArray.getJSONObject(0);
                    carname.clear();
                    cartype.clear();
                    JSONArray jsonArray_carS = jsonResponse.getJSONArray("carnInfo");

                    for (int i = 0; i < jsonArray_carS.length(); i++) {
                        JSONObject responsS = jsonArray_carS.getJSONObject(i);
                        String carNameString = responsS.getString("carname").trim();
                        String carTypeString = responsS.getString("cartype").trim();
                        Log.println(Log.ASSERT, "carNameString", carNameString);
                        Log.println(Log.ASSERT, "carTypeString", carTypeString);


                        StrArr[i] = carNameString;


                        if (carTypeString.equalsIgnoreCase("Sedan")) {
                            cards.add(new CardHelper(gradient2, R.drawable.car, carNameString));
                        } else {
                            cards.add(new CardHelper(gradient3, R.drawable.suvcar, carNameString));

                        }

                        Log.println(Log.ASSERT, "ARRAY SSS", StrArr[i] + " ");

                        adapter = new adapterCard(cards, HomeFragment.this);
                        carRecycler.setAdapter(adapter);
                        carRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
//                        Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }

        }) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

        Log.println(Log.ASSERT, "CarType||size", cartype.size() + "");
        for (int i = 0; cartype.size() > i; i++) { // ******************************Return it to car TypE *********************

        }


        //////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("/n cards size " + cards.size());
        adapter = new adapterCard(cards, this);
        //adapter = new adapterCard(this.getActivity(), carname, cartype, carid ,this);
        carRecycler.setAdapter(adapter);
        carRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

    }
    void storeDataInArrays(){

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