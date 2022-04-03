package com.example.login22_01_19_h1.SpinnerCar;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.login22_01_19_h1.R;

import java.util.ArrayList;

public class CarItemAdapterSecond extends ArrayAdapter<CarRowItem> {

        public CarItemAdapterSecond(Context context, ArrayList<CarRowItem> countryList) {
            super(context, 0, countryList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return initView(position, convertView, parent);
        }

        private View initView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(
                        R.layout.car_spinner_item_row, parent, false
                );
            }

            ImageView imageViewBrand = convertView.findViewById(R.id.image_view_brand_Second);
            TextView textViewName = convertView.findViewById(R.id.text_view_name_second);

            CarRowItem currentItem = getItem(position);

            if (currentItem != null) {
                imageViewBrand.setImageBitmap(currentItem.getBrandImage());
                textViewName.setText(currentItem.getCarName());
            }

            return convertView;
        }
    }

