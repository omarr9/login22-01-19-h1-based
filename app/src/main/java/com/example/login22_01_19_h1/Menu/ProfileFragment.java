package com.example.login22_01_19_h1.Menu;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.login22_01_19_h1.Constants;
import com.example.login22_01_19_h1.DBHelper;
import com.example.login22_01_19_h1.SpinnerCar.CarItemAdapterSecond;
import com.example.login22_01_19_h1.SpinnerCar.CarRowItem;
import com.example.login22_01_19_h1.SpinnerCompany.CompanyItemAdapter;
//import com.example.login22_01_19_h1.MainActivitySpinner;
import com.example.login22_01_19_h1.R;
import com.example.login22_01_19_h1.SpinnerCompany.RowItem;
import com.example.login22_01_19_h1.SpinnerType.RowItemType;
import com.example.login22_01_19_h1.SpinnerType.TypeItemAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;


public class ProfileFragment extends Fragment {
    private ArrayList<RowItem> mCompanyBrandList;
    private CompanyItemAdapter mAdapter;

    private ArrayList<RowItemType> mCarTypeList;
    private TypeItemAdapter mTypeAdapter;


    String spinTextT = "";

    String typecar = "";
    String Company = "";
    public String car = "";
    static int counterCarId = 3;
    DBHelper dbHelper;

    private ArrayList<CarRowItem> mToyotaCar;
    private ArrayList<CarRowItem> mFordCar;
    private ArrayList<CarRowItem> mHyundaiCar;
    private ArrayList<CarRowItem> mMercedesCar;

    private CarItemAdapterSecond mAdapterSecond;
    private ProgressDialog progressDialog;


    ////

    ///


//    private boolean clickedItemCompanyName1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.activity_spinner_main, container, false);


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progressDialog = new ProgressDialog(getActivity());

        initList();
        mCarTypeList = new ArrayList<>();
        mCarTypeList.add(new RowItemType("Sedan"));
        mCarTypeList.add(new RowItemType("SUV"));


        Spinner spinnerComapnires = (Spinner) getView().findViewById(R.id.spinner_comapnires);
        //---

        Spinner spinnerCar = (Spinner) getView().findViewById(R.id.spinner_car);
        Spinner spinnerType = (Spinner) getView().findViewById(R.id.spinner_type);
        TextView textViewType = (TextView) getView().findViewById(R.id.text_view_type);
        TextView textViewCompany = (TextView) getView().findViewById(R.id.text_view_company);
        TextView textViewCar = (TextView) getView().findViewById(R.id.text_view_car);
        //Type Spinner
        mTypeAdapter = new TypeItemAdapter(getActivity(), mCarTypeList);
        spinnerType.setAdapter(mTypeAdapter);

        //Company Spinner
        mAdapter = new CompanyItemAdapter(getActivity(), mCompanyBrandList);
        spinnerComapnires.setAdapter(mAdapter);

        mFordCar = new ArrayList<>();
        mFordCar.add(new CarRowItem("EDGE"));
        mFordCar.add(new CarRowItem("EXPEDITION"));
        mFordCar.add(new CarRowItem("EXPLORER" , R.drawable.explorer));
        mFordCar.add(new CarRowItem("F-SERIES"));
        mFordCar.add(new CarRowItem("Fusion", R.drawable.fusion));


        mHyundaiCar = new ArrayList<>();

        mHyundaiCar.add(new CarRowItem("Sonata", R.drawable.sonata));
        mHyundaiCar.add(new CarRowItem("Elantra" , R.drawable.elantra));
        mHyundaiCar.add(new CarRowItem("Accent", R.drawable.accent));
        mHyundaiCar.add(new CarRowItem("Santa fe" , R.drawable.santa_fe));

        mMercedesCar = new ArrayList<>();
        mMercedesCar.add(new CarRowItem("Mercedes-Benz GLA"));
//        mMercedesCar.add(new CarRowItem("Mercedes-Benz E-Class"));
        mMercedesCar.add(new CarRowItem("Mercedes-Benz S-Class" , R.drawable.s_class));
        mMercedesCar.add(new CarRowItem("Mercedes-Benz AMG G 63", R.drawable.a_class));
        mMercedesCar.add(new CarRowItem("Mercedes-Benz G-Class" , R.drawable.g_class));


        mToyotaCar = new ArrayList<>();
        mToyotaCar.add(new CarRowItem("Avalon"));
        mToyotaCar.add(new CarRowItem("Camry", R.drawable.camry));
        mToyotaCar.add(new CarRowItem("Corolla" , R.drawable.corolla));
        mToyotaCar.add(new CarRowItem("Yaris" , R.drawable.yaris));


        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RowItemType clickedItem = (RowItemType) parent.getItemAtPosition(position);
                String clickedItemType = (clickedItem.getTypeName());
                typecar = clickedItemType;




            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        spinnerComapnires.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RowItem clickedItem = (RowItem) parent.getItemAtPosition(position);
                String clickedItemCompanyNameS = (clickedItem.getCompanyName());
                if (position == 0) {
                    mAdapterSecond = new CarItemAdapterSecond(getActivity(), mFordCar);
                    Company = "Ford";


                }
                if (position == 1) {
                    mAdapterSecond = new CarItemAdapterSecond(getActivity(), mHyundaiCar);
                    Company = "Hyundai";

                }
                if (position == 2) {
                    mAdapterSecond = new CarItemAdapterSecond(getActivity(), mMercedesCar);
                    Company = "Mercedes";

                }
                if (position == 3) {
                    mAdapterSecond = new CarItemAdapterSecond(getActivity(), mToyotaCar);
                    Company = "Toyota";


                }

                spinnerCar.setAdapter(mAdapterSecond);
//                Toast.makeText(getActivity(), clickedItemCompanyNameS + " selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        spinnerCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CarRowItem clickedItem = (CarRowItem) parent.getItemAtPosition(position);
                String clickedCarName = clickedItem.getCarName();
                car = clickedCarName;
                Toast.makeText(getActivity(), clickedCarName + " selected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Button bt = getActivity().findViewById(R.id.ButtonSpinnerPage);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textViewType.setText(typecar + "");
                textViewCompany.setText(Company + "");
                textViewCar.setText(car + "");

//                }


                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_USERCARS, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
////                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//
//                            Toast.makeText(getContext(), jsonObject.getString("message")+"+JSON", Toast.LENGTH_LONG).show();
//
//
//                        } catch (Exception e) {
//                            JSONObject jsonObject2 ;
//                            try {
//                                jsonObject2 = new JSONObject(response);
//                            Toast.makeText(getContext(), jsonObject2.getString("message")+"+JSON", Toast.LENGTH_LONG).show();
//                            textViewCar.setText("From Inside Jsonn");
//                            } catch (JSONException jsonException) {
//                                jsonException.printStackTrace();
//                            }
//                            e.printStackTrace();
//
//
//
//                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
//                        Toast.makeText(getContext().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }

                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        int i = 0;
                        params.put("cartype", typecar + "");
                        params.put("carname", car + "");
//                        params.put("VehicleRegistration ",);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);


            }
        });


    }


    private void initList() {
        mCompanyBrandList = new ArrayList<>();
        mCompanyBrandList.add(new RowItem("Ford", R.drawable.ic_ford_logo_foreground));
        mCompanyBrandList.add(new RowItem("Hyundai", R.drawable.hundai2_logo));
        mCompanyBrandList.add(new RowItem("Mercedes", R.drawable.mercedes_logo_sec));
        mCompanyBrandList.add(new RowItem("Toyota", R.drawable.toyota_logo));
    }


    private void initListCar() {

    }

    private void carSpinner() {


    }
}
