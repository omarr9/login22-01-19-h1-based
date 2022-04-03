package com.example.login22_01_19_h1.Menu;

//import android.app.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
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
import com.example.login22_01_19_h1.MainActivityNotification;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.RequestHandlerSingleton;
import com.example.login22_01_19_h1.SharedPrefManger;
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
    ArrayList<String> carname, cartype, carid;
    String carIdString;
    Button addnewcar;
    Button button;


    RecyclerView carRecycler;
    RecyclerView.Adapter adapter;

    private ProgressDialog progressDialog;
    ArrayList<CardHelper> cards = new ArrayList<>();

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //Hooks
        carRecycler = getView().findViewById(R.id.my_recycler);
        progressDialog = new ProgressDialog(getActivity());

        button = getView().findViewById(R.id.Next);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //************** Important For Enable The Notifications ***********************************
 //                Intent intent = new Intent(getActivity(), MainActivityNotification.class);
//                startActivity(intent);

                //************** It's The Way To Move From One Fragment To Another  ***********************************

                CentersFragment fragment = new CentersFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("VALUE", s);
//        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

            }
        });

        getcars();

    }
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_home, container, false);

    }

    private void getcars() {
        cards.clear();
        //cards.add(new CardHelper("Mazda"));
        //imgtypeview = getView().findViewById(R.id.car_image);

        carname = new ArrayList<>();
        cartype = new ArrayList<>();

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

                    System.out.println(jsonArray_carS.length());
                    for (int i = 0; i < jsonArray_carS.length(); i++) {
                        JSONObject responsS = jsonArray_carS.getJSONObject(i);
                        System.out.println("here is the output : "+responsS);
                        int carid = Integer.parseInt(responsS.getString("id").trim());
                        String carCompany = responsS.getString("carCompany").trim();
                        String carNameString = responsS.getString("carname").trim();
                        String carTypeString = responsS.getString("cartype").trim();
                        System.out.println("Testing -------------------- here" + carTypeString);
                        //carIdString = responsS.getString("ID").trim();

                        if (carTypeString.equalsIgnoreCase("Sedan")) {
                            cards.add(new CardHelper( R.drawable.car, carCompany , carNameString, carid));
                        } else {
                            cards.add(new CardHelper( R.drawable.suvcar, carCompany , carNameString, carid));
                        }

                        adapter = new adapterCard(cards, HomeFragment.this);
                        carRecycler.setAdapter(adapter);
                        carRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

                    }
                } catch (JSONException e) {
                    //you dont have any card, add new crad
                    TextView addcarmes = getView().findViewById(R.id.addcarmes);
                    //Button add new car
                    addcarmes.setText("Oh it seems you didn't add any car yet , you can add new car from the plus sign");
                    e.printStackTrace();
                }
                System.out.println("Testing -------------------- 4");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
//                        Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }

        }) {

        };
        // - Put the Request in a RequestQueue usning singelton
        RequestHandlerSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        addnewcar = getView().findViewById(R.id.addnewcar);
        addnewcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment fragment = new ProfileFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("VALUE", s);
//        fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public void onphoneListClick(int clickedItemIndex, String s , ArrayList<CardHelper> Cardinfo) {

        Intent mIntent;
//        switch (clickedItemIndex){
//            case 0: //first item in Recycler view

//
        System.out.println("Testing ------- "+Cardinfo.get(clickedItemIndex).getTitle());
        System.out.println("Testing ------- "+Cardinfo.get(clickedItemIndex).getCompany());
        System.out.println("Testing ------- "+Cardinfo.get(clickedItemIndex).getid());

        // To pass some value from FragmentA

//        Log.println(Log.ASSERT, "carIDInsideMethod", "" + s);
//        BookingFragment fragment = new BookingFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("VALUE", s);
//        fragment.setArguments(bundle);
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();


    }


//                mIntent  = new Intent(HomeFragment.this, NavHostFragment.findNavController(BookingFragment));
//                startActivity(mIntent);
//                break;

//        }


}