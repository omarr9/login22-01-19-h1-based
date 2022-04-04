package com.example.login22_01_19_h1.Menu;

import android.app.ProgressDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.RequestHandlerSingleton;
import com.example.login22_01_19_h1.SharedPrefManger;
import com.example.login22_01_19_h1.SliderHistory.ReservsationHelper;
import com.example.login22_01_19_h1.SliderHistory.adapterCardHistory;
import com.example.login22_01_19_h1.sliderhome.CardHelper;
import com.example.login22_01_19_h1.sliderhome.adapterCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//public class HistoryFragment extends Fragment  {
public class HistoryFragment extends Fragment implements adapterCardHistory.ListItemClickListenerReserv {

    @org.jetbrains.annotations.Nullable
    TextView carnameview;
    ImageView imgtypeview;
    ArrayList<String> carname, cartype, carid;
    ArrayList<String> CarNAmeArray = new ArrayList<>();
    ArrayList<String> CarTypeArray = new ArrayList<>();
    ArrayList<String> OurCarIDArray = new ArrayList<>();
    ArrayList<String> CompanyIDArray = new ArrayList<>();
    ArrayList<String> CarImageArray = new ArrayList<>();

    //CustomAdapter customAdapter;
    String carIdString;

    String CarType ;
    String CarName ;
    String OurCarID ;
    String CompanyID ;
    String CarImage ;


    String MCenterID;
    String Name;
    String Address;
    String  GoogleMaps;
    String Capacity;

    RecyclerView ReservRecycler;
    RecyclerView.Adapter adapter;
    GradientDrawable gradient1;
    GradientDrawable gradient2;
    GradientDrawable gradient3;
    GradientDrawable gradient4;


    public void onViewCreated(View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        //Hooks
        ReservRecycler = getView().findViewById(R.id.my_recycler_history);
        progressDialog = new ProgressDialog(getActivity());


        ReservRecycler();

    }


    private ProgressDialog progressDialog;
    ArrayList<ReservsationHelper> cards = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main_card_history, container, false);
    }


    private void ReservRecycler() {


        gradient2 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffd4cbe5, 0xffd4cbe5});
        gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xff7adccf, 0xff7adccf});
        gradient3 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xfff7c59f, 0xFFf7c59f});
        gradient4 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffb8d7f5, 0xffb8d7f5});


        cards.clear();

//        cards.add(new ReservsationHelper("Mazda"));
//        cards.add(new ReservsationHelper( R.drawable.car, "2022-06-4","1:30","AL-Morjan ",gradient2));


        imgtypeview = getView().findViewById(R.id.car_image);

        carname = new ArrayList<>();
        cartype = new ArrayList<>();


        Log.println(Log.ASSERT, "reservationIdString", "Hiiiiiiiiiiiiiiiii");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_USER_BOOKING_INFO, new Response.Listener<String>() {

            public void onResponse(String json_CarName_Arr) {
                progressDialog.dismiss();
                try {
                    Log.println(Log.ASSERT, "History", "Before");

                    JSONArray array = new JSONArray(json_CarName_Arr);
                    Log.println(Log.ASSERT, "History", "After ");


                    for (int i = 0; i < array.length(); i++) {
                        //getting product object from json array
                        Log.println(Log.ASSERT, "History", "Inside lOOP Before ");

                        JSONObject jsonData = array.getJSONObject(i);
                        //adding the product to product list
//                        cards.add(new CardHelper(

                              CarType =  jsonData.getString("CarType");
                         CarName =   jsonData.getString("CarName");
                         OurCarID =   jsonData.getString("OurCarID");
                         CompanyID =    jsonData.getString("CompanyID");
                        CarImage= jsonData.optString("CarImg");

                        Log.println(Log.ASSERT, "History", "Inside lOOP After ");
                        CarTypeArray.add(CarType);
                        CarNAmeArray.add(CarName);
                        OurCarIDArray.add(OurCarID);
                        CompanyIDArray.add(CompanyID);
                        CarImageArray.add(CarImage);

//                        String CarType =      jsonData.optString("CarImg");
//                        ));
//                        Log.println(Log.ASSERT, "History", CarType);
//                        Log.println(Log.ASSERT, "History", CarName);
//                        Log.println(Log.ASSERT, "History", CompanyID);
                    }

                    for (int i = 0; i < CarNAmeArray.size(); i++) {
                        Log.println(Log.ASSERT, "Array-History", CarNAmeArray.get(i));


                    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_GET_USER_BOOKING_INFO_CENTERS, new Response.Listener<String>() {

                        public void onResponse(String json_Mcenter_Arr) {
                            progressDialog.dismiss();
                            try {
//                                Log.println(Log.ASSERT, "History", "Before");

                                JSONArray array = new JSONArray(json_Mcenter_Arr);
//                                Log.println(Log.ASSERT, "History", "After ");


                                for (int i = 0; i < array.length(); i++) {
                                    //getting product object from json array
//                                    Log.println(Log.ASSERT, "History", "Inside lOOP Before ");

                                    JSONObject jsonData = array.getJSONObject(i);
                                    //adding the product to product list
//                        cards.add(new CardHelper(

                                    MCenterID =  jsonData.getString("MCenterID");
//                                    CompanyID =   jsonData.getString("CompanyID");
                                    Name =   jsonData.getString("Name");
                                    Address =    jsonData.getString("Address");
                                    GoogleMaps =    jsonData.getString("GoogleMaps");
                                    Capacity =    jsonData.getString("Capacity");


                                    Log.println(Log.ASSERT, "History-Centers", MCenterID);
                        Log.println(Log.ASSERT, "History-Centers", Name);
                        Log.println(Log.ASSERT, "History-Centers", Address);

                        cards.add(new ReservsationHelper(CarImageArray.get(i),  "2022", "10;30",Address , CarTypeArray.get(i) ,CarNAmeArray.get(i) ,Name ,"https://www.youtube.com", gradient1, R.drawable.ic_directions ));
                                }


                                cards.add(new ReservsationHelper(CarImageArray.get(0),  "2022", "10;30","One" , "Two" ,"Three ",Name ,  "https://www.google.com",gradient1,R.drawable.ic_directions));
                                cards.add(new ReservsationHelper(CarImageArray.get(0),  "2024", "10;30","One" , "Two" ,"Three ",Name ,  "https://www.youtube.com",gradient2,R.drawable.ic_directions));

                                adapter = new adapterCardHistory(getContext(),cards, HistoryFragment.this);
                                //adapter = new adapterCard(this.getActivity(), carname, cartype, carid ,this);
                                ReservRecycler.setAdapter(adapter);
                                ReservRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.println(Log.ASSERT, "reservationIdString", "Error : " + e.toString());

                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
//                        Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            Log.println(Log.ASSERT, "reservationIdString", "onErrorResponser : " + error.toString());

                        }

                    })

                    {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            int i = 0;
//                    SharedPrefManger.getInstance(getActivity()).getCopmanyId();
                            params.put("UserID", String.valueOf(SharedPrefManger.getInstance(getActivity()).getUserId()));
                            Log.println(Log.ASSERT, "reservationIdString", "Check Final-Centers:");

                            return params;
                        }
                    };


//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);

                    RequestHandlerSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


                    adapter = new adapterCardHistory(getContext(),cards, HistoryFragment.this);
                    //adapter = new adapterCard(this.getActivity(), carname, cartype, carid ,this);
                    ReservRecycler.setAdapter(adapter);
                    ReservRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.println(Log.ASSERT, "reservationIdString", "Error : " + e.toString());

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
//                        Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                Log.println(Log.ASSERT, "reservationIdString", "onErrorResponser : " + error.toString());

            }

        })

        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                int i = 0;
//                    SharedPrefManger.getInstance(getActivity()).getCopmanyId();
                params.put("UserID", String.valueOf(SharedPrefManger.getInstance(getActivity()).getUserId()));
                Log.println(Log.ASSERT, "reservationIdString", "Check Final:");

                return params;
            }
        };


//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);

        RequestHandlerSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


        adapter = new adapterCardHistory(getContext(),cards, this);
        //adapter = new adapterCard(this.getActivity(), carname, cartype, carid ,this);
        ReservRecycler.setAdapter(adapter);
        ReservRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));


    }


    @Override
    public void onViewListClick(int clickedItemIndex, String title) {

    }
}