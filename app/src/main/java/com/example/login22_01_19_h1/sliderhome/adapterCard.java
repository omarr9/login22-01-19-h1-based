package com.example.login22_01_19_h1.sliderhome;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login22_01_19_h1.MainActivity;
import com.example.login22_01_19_h1.Menu.HomeFragment;
import com.example.login22_01_19_h1.R;

import java.util.ArrayList;

public class adapterCard extends RecyclerView.Adapter<adapterCard.PhoneViewHold>  {

    private Context context;
    private Activity activity;
    private ArrayList carname, cartype, carid;

    ArrayList<CardHelper> Cardinfo;
    final private ListItemClickListener mOnClickListener;

    public adapterCard(ArrayList<CardHelper> phoneLaocations, HomeFragment listener) {
        this.Cardinfo = phoneLaocations;
        mOnClickListener = listener;
    }

    /*public adapterCard(Activity activity, ArrayList carname, ArrayList cartype, ArrayList carid , SettingsFragment listener){
        this.activity = activity;
        //this.context = context;
        this.carname = carname;
        this.cartype = cartype;
        this.carid = carid;
        mOnClickListener = listener;
    }*/

    @NonNull

    @Override
    public PhoneViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carddesign, parent, false);
        return new PhoneViewHold(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHold holder, int position) {
        CardHelper carhelper = Cardinfo.get(position);
        holder.image.setImageResource(carhelper.getImage());
        holder.title.setText(carhelper.getTitle());
    }

    @Override
    public int getItemCount() {
        return Cardinfo.size();

    }

    public interface ListItemClickListener {
        void onphoneListClick(int clickedItemIndex, String title,ArrayList<CardHelper> Cardinfo);
    }

    public class PhoneViewHold extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView title;
        RelativeLayout relativeLayout;

        public int getItemCount() {
            return carid.size();
        }


        public PhoneViewHold(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //hooks

            image = itemView.findViewById(R.id.car_image);
            title = itemView.findViewById(R.id.phone_title);
            relativeLayout = itemView.findViewById(R.id.background_color);

        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onphoneListClick(clickedPosition, title.getText().toString(),Cardinfo);
        }
    }

}
