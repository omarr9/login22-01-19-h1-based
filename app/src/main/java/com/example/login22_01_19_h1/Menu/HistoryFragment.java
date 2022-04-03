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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.RequestHandlerSingleton;
import com.example.login22_01_19_h1.SliderHistory.ReservsationHelper;
import com.example.login22_01_19_h1.SliderHistory.adapterCardHistory;
import com.example.login22_01_19_h1.sliderhome.CardHelper;
import com.example.login22_01_19_h1.sliderhome.adapterCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//public class HistoryFragment extends Fragment  {
public class HistoryFragment extends Fragment implements adapterCardHistory.ListItemClickListenerReserv {

    @org.jetbrains.annotations.Nullable
    TextView carnameview;
    ImageView imgtypeview;
    ArrayList<String> carname, cartype, carid;
    //CustomAdapter customAdapter;
    String carIdString;

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

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_RESERVATION_HISTORY, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Log.println(Log.ASSERT, "reservationIdString", "Response");


                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonResponse = jsonArray.getJSONObject(0);
                    carname.clear();
                    cartype.clear();
                    JSONArray jsonArray_carS = jsonResponse.getJSONArray("response");

                    for (int i = 0; i < jsonArray_carS.length(); i++) {
                        JSONObject responsS = jsonArray_carS.getJSONObject(i);

                        String reservationIdString = responsS.getString("id").trim();
                        String reservationLocationString = responsS.getString("Address").trim();
                        String reservationDateString = responsS.getString("Date").trim();
                        String reservationTimeString = responsS.getString("Time").trim();

                        Log.println(Log.ASSERT, "LoginPage", reservationTimeString);
                        ///////////////////////////////////////////////////////////////////////////////////


//                    }


//                        Log.println(Log.ASSERT, "reservationIdString", reservationIdString);
//                        Log.println(Log.ASSERT, "reservationLocationString", reservationLocationString);
//                        Log.println(Log.ASSERT, "reservationDateString", reservationDateString);


//                        if (carTypeString.equalsIgnoreCase("Sedan")) {
                        cards.add(new ReservsationHelper(R.drawable.car, reservationDateString, reservationTimeString, reservationLocationString, gradient2));
//                        } else {
//                            cards.add(new CardHelper(gradient3, R.drawable.suvcar, carNameString));

//                        }

                    }

                    adapter = new adapterCardHistory(cards, HistoryFragment.this);
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

        }) {

        };
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);

        RequestHandlerSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


        adapter = new adapterCardHistory(cards, this);
        //adapter = new adapterCard(this.getActivity(), carname, cartype, carid ,this);
        ReservRecycler.setAdapter(adapter);
        ReservRecycler.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));


    }


    @Override
    public void onViewListClick(int clickedItemIndex, String title) {

    }
}