package com.example.login22_01_19_h1.Menu;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.Dates;
import com.example.login22_01_19_h1.DatesAdapter;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.RequestHandlerSingleton;


import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.Map;

public class BookingFragment extends Fragment implements DatesAdapter.OnDateListener {

    RecyclerView recyclerView;
    ArrayList<Dates> dates = new ArrayList<>();
    DatesAdapter datesadapter;
    ArrayList<Button> times = new ArrayList<>();
    ViewGroup layout;
    String[][] shcdel;
    int dayposition;
    private ProgressDialog progressDialog;
    String[][] calender = new String[50][20];
    TextView feedback;
    Button confirm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_main_card_date, container, false);

    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Initializing variables
        recyclerView = getView().findViewById(R.id.dates);
        progressDialog = new ProgressDialog(getActivity());

        stepone();
        DateRecycler();

    }

    private void stepone() {
        //Onclick listener for all buttons ( 16 )
        Button b1 = getView().findViewById(R.id.b1);
        times.add(b1);
        Button b2 = getView().findViewById(R.id.b2);
        times.add(b2);
        Button b3 = getView().findViewById(R.id.b3);
        times.add(b3);
        Button b4 = getView().findViewById(R.id.b4);
        times.add(b4);
        Button b5 = getView().findViewById(R.id.b5);
        times.add(b5);
        Button b6 = getView().findViewById(R.id.b6);
        times.add(b6);
        Button b7 = getView().findViewById(R.id.b7);
        times.add(b7);
        Button b8 = getView().findViewById(R.id.b8);
        times.add(b8);
        Button b9 = getView().findViewById(R.id.b9);
        times.add(b9);
        Button b10 = getView().findViewById(R.id.b10);
        times.add(b10);
        Button b11 = getView().findViewById(R.id.b11);
        times.add(b11);
        Button b12 = getView().findViewById(R.id.b12);
        times.add(b12);
        Button b13 = getView().findViewById(R.id.b13);
        times.add(b13);
        Button b14 = getView().findViewById(R.id.b14);
        times.add(b14);
        Button b15 = getView().findViewById(R.id.b15);
        times.add(b15);
        Button b16 = getView().findViewById(R.id.b16);
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

        feedback = getView().findViewById(R.id.feedback);
        confirm = getView().findViewById(R.id.confirm);
        feedback.setText("");
        confirm.setVisibility(View.GONE);
        confirm.setTextColor(Color.parseColor("#ffffff"));
        confirm.setBackground(getActivity().getResources().getDrawable(R.drawable.buttondesign));
    }

    private void DateRecycler() {


        Bundle bun = getArguments();
        if (bun != null) {
            String value = bun.getString("VALUE");
            Log.println(Log.ASSERT, "value :", "" + value);
        }
        System.out.println("Test Mes -------- Making stringRequest ");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_SCHEDULE_RETRIVE, new Response.Listener<String>() {
            @Override
            public void onResponse(String json_re) {
                System.out.println("Test Mes -------- onResponse");
                progressDialog.dismiss();
                JSONArray jsonArray = null;
                //Essam
                try {
                    jsonArray = new JSONArray(json_re);
                    JSONObject jsonResponse = jsonArray.getJSONObject(0);
//                    dates.clear();
                    JSONArray jsonArray_carS = jsonResponse.getJSONArray("schedInfo");
                    Log.println(Log.ASSERT, "jsonArray_carS", jsonArray_carS.toString());
                    System.out.println("Testing ---------------------  onResponse 2");
                    for (int i = 0; i < jsonArray_carS.length() && i < 160; i++) {
                        System.out.println("--------lenght : " + jsonArray_carS.length());
                        JSONObject responsS = jsonArray_carS.getJSONObject(i);
                        String DateString = responsS.getString("date").trim();
                        String TimeString = responsS.getString("Time").trim();
                        String DayString = responsS.getString("Day").trim();
                        String Avaliable = responsS.getString("Avilibality").trim();

                        Log.println(Log.ASSERT, "DateString", DateString);
                        Log.println(Log.ASSERT, "DayString", DayString);
                        //Essam
                        System.out.println("Testing ---------------------  onResponse 3");

                        if (Avaliable.equalsIgnoreCase("Avaliable")) {
                            if (calender[0][0] == null) {
                                calender[0][0] = DayString;
                                calender[0][1] = DateString;
                                calender[0][2] = TimeString;
                                System.out.println("Testing --------------------- inside if");
                            } else {
                                boolean check = false;
                                int k = 0;
                                for (k = 0; k < calender.length; k++) {
                                    if (calender[k][1] == null) {
                                        check = false;
                                        break;
                                    } else if (calender[k][1].equalsIgnoreCase(DateString)) {
                                        check = true;
                                        break;
                                    }
                                }

                                if (check == true) {
                                    int len2;
                                    for (len2 = 0; len2 < calender[k].length; len2++) {
                                        if (calender[k][len2] == null) {
                                            break;
                                        }
                                    }
                                    calender[k][len2] = TimeString;
                                } else {
                                    calender[k][0] = DayString;
                                    calender[k][1] = DateString;
                                    calender[k][2] = TimeString;
                                }
                                System.out.println("Testing --------------------- inside else if");
                            }
                        } else {
                            System.out.println("Testing ---------------------  not Avaliable");

                        }
                    }
                } catch (JSONException e) {
                    System.out.println("Testing ---------------------  JSONException Error");
                    e.printStackTrace();
                }

                //for testin :
                if (calender == null) {
                    System.out.println("Error ----------- Attempt to read from null array");
                } else {
                    for (int i = 0; i < calender.length; i++) { //this equals to the row in our matrix.
                        for (int j = 0; j < calender[i].length; j++) { //this equals to the column in each row.
                            System.out.print(calender[i][j] + " ");
                        }
                        System.out.println(); //change line on console as row comes to end in the matrix.
                    }
                }

                System.out.println("Test Mes -------- Calling laststep");
                laststep();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
//                        Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

            }

        }) {


            @androidx.annotation.Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                int i = 0;
//                params.put("Booked",  "1");
                return params;
            }
        };

//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//        requestQueue.add(stringRequest);
//        requestQueue.start();

        RequestHandlerSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

        System.out.println("Testing Done !! ----- ");
    }

    public void laststep() {
        for (int i = 0; i < calender.length && calender[i][0] != null; i++) {
            Dates date = new Dates(calender[i][1], calender[i][0]);
            //Dates date = new Dates(textdate[i], daysname[i]);
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
        layout = getView().findViewById(R.id.layout_container_buttons);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setText("");
                button.setBackgroundTintList(getActivity().getColorStateList(R.color.buttoncolors));
                //button.setBackgroundResource(android.R.drawable.btn_default);
                button.setVisibility(View.GONE);
            }
        }

    }


    @Override
    public void onDateClick(int position) {
        dayposition = position;
        clearbuttons();
        for (int i = 0; i < layout.getChildCount() && calender[position][i + 2] != null; i++) {
            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setText(calender[position][i + 2]);
//                int drawable = R.drawable.red_button_border;
//                button.setBackgroundResource(drawable);
                button.setVisibility(View.VISIBLE);
            }
        }
    }

    public void clearbuttons() {
        confirm.setVisibility(View.GONE);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setText("");
                button.setSelected(false);
                button.setTextColor(Color.parseColor("#000000"));
                button.setVisibility(View.GONE);
            }
        }
    }

    public void appointmentInfo(Button button) {
        resetcolors();
        boolean aviliblity = false;
        feedback.setText("");
        //button.setBackgroundColor(Color.parseColor("#87431D"));
        button.setSelected(true);
        button.setTextColor(Color.parseColor("#ffffff"));
        confirm.setVisibility(View.VISIBLE);
        confirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                // check aviliblity first usnig php requset with lock
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_SCHEDULE_Update, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        if (response.contains("Done")) {
                            System.out.println("--------------------Done -----------");
                            Toast.makeText(getContext(), "Thanks!", Toast.LENGTH_LONG).show();
                            //take him to next page
                        } else {
                            System.out.println("--------------------Full sorry -----------");
                            Toast.makeText(getContext(), "Sorry! The requested time has already been filled, the available times have been updated, please rebook", Toast.LENGTH_LONG).show();
                            refresh();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                    }

                }) {
                    @androidx.annotation.Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        System.out.println("Test Mes -------- edit database");
                        Map<String, String> params = new HashMap<>();
                        params.put("date", calender[dayposition][1]);
                        params.put("Time", button.getText().toString());
                        return params;
                    }
                };


//                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
//                requestQueue.add(stringRequest);

                RequestHandlerSingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);


            }
        });
        System.out.println("--------- Appointment Info --------------");
        System.out.println("The Day is : " + calender[dayposition][0]);
        System.out.println("The Date is : " + calender[dayposition][1]);
        System.out.println("The Time is : " + button.getText());
    }

    public void resetcolors() {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setSelected(false);
                button.setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    public void refresh() {
        calender = null;
        calender = new String[50][20];
        DateRecycler();
    }


}


