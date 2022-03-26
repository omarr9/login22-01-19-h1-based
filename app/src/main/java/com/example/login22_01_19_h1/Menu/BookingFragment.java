package com.example.login22_01_19_h1.Menu;

//import android.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.Dates;
import com.example.login22_01_19_h1.DatesAdapter;
import com.example.login22_01_19_h1.MainActivity_Card_Date;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.sliderhome.CardHelper;
import com.example.login22_01_19_h1.sliderhome.adapterCard;


import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import java.util.ArrayList;

public class BookingFragment extends Fragment implements DatesAdapter.OnDateListener {

    RecyclerView recyclerView;
    ArrayList<Dates> dates = new ArrayList<>();
    DatesAdapter datesadapter;
    ArrayList<Button> times;
    ViewGroup layout;
    String[][] shcdel;
    int dayposition;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_main_card_date, container, false);

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Initializing variables
        recyclerView = getView().findViewById(R.id.dates);
        progressDialog = new ProgressDialog(getActivity());


        DateRecycler();


    }

    private void DateRecycler() {


        //Onclick listener for all buttons ( 16 )
        times = new ArrayList<>();
        Button b1 = (Button) getView().findViewById(R.id.b1);
        times.add(b1);
        Button b2 = (Button) getView().findViewById(R.id.b2);
        times.add(b2);
        Button b3 = (Button) getView().findViewById(R.id.b3);
        times.add(b3);
        Button b4 = (Button) getView().findViewById(R.id.b4);
        times.add(b4);
        Button b5 = (Button) getView().findViewById(R.id.b5);
        times.add(b5);
        Button b6 = (Button) getView().findViewById(R.id.b6);
        times.add(b6);
        Button b7 = (Button) getView().findViewById(R.id.b7);
        times.add(b7);
        Button b8 = (Button) getView().findViewById(R.id.b8);
        times.add(b8);
        Button b9 = (Button) getView().findViewById(R.id.b9);
        times.add(b9);
        Button b10 = (Button) getView().findViewById(R.id.b10);
        times.add(b10);
        Button b11 = (Button) getView().findViewById(R.id.b11);
        times.add(b11);
        Button b12 = (Button) getView().findViewById(R.id.b12);
        times.add(b12);
        Button b13 = (Button) getView().findViewById(R.id.b13);
        times.add(b13);
        Button b14 = (Button) getView().findViewById(R.id.b14);
        times.add(b14);
        Button b15 = (Button) getView().findViewById(R.id.b15);
        times.add(b15);
        Button b16 = (Button) getView().findViewById(R.id.b16);
        times.add(b16);

        for (int i = 0; i < times.size(); i++) {
            Log.println(Log.ASSERT, "ARRAY SSS", times.get(i) + " ");
            times.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    for (int i = 0; i < times.size(); i++) {
                        if (view.getId() == times.get(i).getId()) {
                            appointmentInfo(times.get(i));


                        }

                    }

                }
            });
        }


        // create the arrays of data
        // it should bring it from DATABASE
        String[] textdate = {"12/12/2022", "13/12/2022", "14/12/2022", "15/12/2022", "16/12/2022", "17/12/2022", "18/12/2022"};
        String[] daysname = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] timing = {"8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM"};
        String[] timing2 = {"9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"};
        shcdel = new String[][]{
                {"Sunday", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM"},
                {"Monday", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
                {"Tuesday", "9:30 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
                {"Wednesday", "9:00 AM", "9:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "4:00 PM", "4:30 PM"},
                {"Thursday", "9:00 AM", "9:30 AM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
                {"Friday", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
                {"Saturday", "9:00 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"}};

        // Retrive Data From Database


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_SCHEDULE_RETRIVE, new Response.Listener<String>() {

            @Override
            public void onResponse(String json_re) {
                progressDialog.dismiss();


                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(json_re);
                    JSONObject jsonResponse = jsonArray.getJSONObject(0);
                    dates.clear();
                    JSONArray jsonArray_carS = jsonResponse.getJSONArray("response");

                    for (int i = 0; i < jsonArray_carS.length(); i++) {
                        JSONObject responsS = jsonArray_carS.getJSONObject(i);
                        String DateString = responsS.getString("date").trim();
                        String DayString = responsS.getString("Day").trim();
                        Log.println(Log.ASSERT, "DateString", DateString);
                        Log.println(Log.ASSERT, "DayString", DayString);


//                        StrArr[i] = carNameString;
//
//
//                        if (carTypeString.equalsIgnoreCase("Sedan")) {
//                            cards.add(new CardHelper(gradient2, R.drawable.car, carNameString));
//                        }else{
//                            cards.add(new CardHelper(gradient3, R.drawable.suvcar,carNameString ));
//
//                        }

//                        Log.println(Log.ASSERT, "ARRAY SSS",StrArr[i]+" ");
//
//                        adapter = new adapterCard(cards, HomeFragment.this);
//                        carRecycler.setAdapter(adapter);
//                        carRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

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


        // adding the dates into the arry
        for (int i = 0; i < textdate.length; i++) {
            Dates date = new Dates(textdate[i], daysname[i]);
            dates.add(date);
        }


        // design herozintal layout

        // Initialize dateAdapter
        datesadapter = new DatesAdapter(dates, this);
        // set dateadapter to recyclerView
        recyclerView.setAdapter(datesadapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // hide all time
        layout = (ViewGroup) getView().findViewById(R.id.layout_container_buttons);
        for (int i = 0; i < layout.getChildCount(); i++) {

            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setText("");
                button.setVisibility(View.GONE);
            }
        }

        // Set the times from the arrat

    }


    @Override
    public void onDateClick(int position) {
        dayposition = position;
        clearbuttons();
        for (int i = 0; i < layout.getChildCount() && i < shcdel[position].length - 1; i++) {

            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setText(shcdel[position][i + 1]);
                button.setVisibility(View.VISIBLE);
            }
        }
    }

    public void clearbuttons() {
        for (int i = 0; i < layout.getChildCount(); i++) {

            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setText("");
                button.setVisibility(View.GONE);
            }
        }
    }

    public void appointmentInfo(Button button) {
        System.out.println("The date is : " + shcdel[dayposition][0]);
        System.out.println("The time is : " + button.getText());

    }
//        public void onClick (View view){
//            for (int i = 0; i < times.size(); i++) {
//                if (view.getId() == times.get(i).getId()) {
//                    appointmentInfo(times.get(i));
//
//
//                }
//
//            }
//
//        }

}


