package com.example.login22_01_19_h1;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity_Card_Date {
// extends AppCompatActivity implements DatesAdapter.OnDateListener , View.OnClickListener {
////
////    // Initializing variables
//    RecyclerView recyclerView;
//    ArrayList <com.example.login22_01_19_h1.Dates> dates;
//    DatesAdapter datesadapter;
//    ArrayList <Button> times;
//    ViewGroup layout;
//    String [][] shcdel;
//    int dayposition ;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_card_date);
//
//        // Assigning variables
//        recyclerView = findViewById(R.id.dates);
//
//
//        //Onclick listener for all buttons ( 16 )
//        times = new ArrayList<>();
//        Button b1 = (Button) findViewById(R.id.b1);
//        times.add(b1);
//        Button b2 = (Button) findViewById(R.id.b2);
//        times.add(b2);
//        Button b3 = (Button) findViewById(R.id.b3);
//        times.add(b3);
//        Button b4 = (Button) findViewById(R.id.b4);
//        times.add(b4);
//        Button b5 = (Button) findViewById(R.id.b5);
//        times.add(b5);
//        Button b6 = (Button) findViewById(R.id.b6);
//        times.add(b6);
//        Button b7 = (Button) findViewById(R.id.b7);
//        times.add(b7);
//        Button b8 = (Button) findViewById(R.id.b8);
//        times.add(b8);
//        Button b9 = (Button) findViewById(R.id.b9);
//        times.add(b9);
//        Button b10 = (Button) findViewById(R.id.b10);
//        times.add(b10);
//        Button b11 = (Button) findViewById(R.id.b11);
//        times.add(b11);
//        Button b12 = (Button) findViewById(R.id.b12);
//        times.add(b12);
//        Button b13 = (Button) findViewById(R.id.b13);
//        times.add(b13);
//        Button b14 = (Button) findViewById(R.id.b14);
//        times.add(b14);
//        Button b15 = (Button) findViewById(R.id.b15);
//        times.add(b15);
//        Button b16 = (Button) findViewById(R.id.b16);
//        times.add(b16);
//        for (int i =0 ; i<times.size(); i++){
//            times.get(i).setOnClickListener(this);
//        }
//
//
//        // create the arrays of data
//        // it should bring it from DATABASE
//        String [] textdate = {"12/12/2022","13/12/2022","14/12/2022","15/12/2022","16/12/2022","17/12/2022","18/12/2022"};
//        String [] daysname = {"Sunday","Monday","Tuesday","Wednesday", "Thursday","Friday","Saturday"};
//        String [] timing = {"8:00 AM","8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:00 AM","11:30 AM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","3:30 PM","4:00 PM","4:30 PM"};
//        String [] timing2 = {"9:00 AM","9:30 AM","10:00 AM","10:30 AM","11:30 AM","1:00 PM","1:30 PM","2:00 PM","2:30 PM","3:00 PM","4:00 PM","4:30 PM"};
//        shcdel = new String[][] {
//                {"Sunday", "8:00 AM", "8:30 AM", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:00 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "3:30 PM", "4:00 PM", "4:30 PM"},
//                {"Monday", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
//                {"Tuesday", "9:30 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
//                {"Wednesday", "9:00 AM", "9:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "4:00 PM", "4:30 PM"},
//                {"Thursday", "9:00 AM", "9:30 AM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
//                {"Friday", "9:00 AM", "9:30 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"},
//                {"Saturday", "9:00 AM", "10:00 AM", "10:30 AM", "11:30 AM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM", "4:00 PM", "4:30 PM"}};
//        // Initializing ArrayList
//        dates = new ArrayList<>();
//
//        // adding the dates into the arry
//        for (int i=0; i<textdate.length ; i++){
//            com.example.login22_01_19_h1.Dates date = new  com.example.login22_01_19_h1.Dates(textdate[i],daysname[i]);
//            dates.add(date);
//        }
//
//        // design herozintal layout
//        LinearLayoutManager layoutManager = new LinearLayoutManager(
//                MainActivity_Card_Date.this,LinearLayoutManager.HORIZONTAL,false
//        );
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        // Initialize dateAdapter
//        datesadapter = new DatesAdapter(MainActivity_Card_Date.this,dates, this);
//        // set dateadapter to recyclerView
//        recyclerView.setAdapter(datesadapter);
//
//        // hide all time
//        layout = (ViewGroup)findViewById(R.id.layout_container_buttons);
//        for (int i = 0; i < layout.getChildCount(); i++) {
//
//            View child = layout.getChildAt(i);
//            if(child instanceof Button)
//            {
//                Button button = (Button) child;
//                button.setText("");
//                button.setVisibility(View.GONE);
//            }
//        }
//
//        // Set the times from the arrat
//
//    }
//
//    @Override
//    public void onDateClick(int position) {
//        dayposition = position;
//        clearbuttons ();
//        for (int i = 0; i < layout.getChildCount() && i < shcdel[position].length-1; i++) {
//
//            View child = layout.getChildAt(i);
//            if(child instanceof Button)
//            {
//                Button button = (Button) child;
//                button.setText(shcdel[position][i+1]);
//                button.setVisibility(View.VISIBLE);
//            }
//        }
//    }
//
//    public void clearbuttons (){
//        for (int i = 0; i < layout.getChildCount(); i++) {
//
//            View child = layout.getChildAt(i);
//            if(child instanceof Button)
//            {
//                Button button = (Button) child;
//                button.setText("");
//                button.setVisibility(View.GONE);
//            }
//        }
//    }
//
//    public void appointmentInfo(Button button){
//        System.out.println("The date is : "+shcdel[dayposition][0]);
//        System.out.println("The time is : "+ button.getText());
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        for (int i=0; i<times.size() ; i++){
//            if (view.getId() == times.get(i).getId()){
//                appointmentInfo(times.get(i));
//
//
//            }
//
//        }
//
//    }
}