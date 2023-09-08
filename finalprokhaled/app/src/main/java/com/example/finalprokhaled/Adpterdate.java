package com.example.finalprokhaled;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class Adpterdate extends RecyclerView.Adapter<Adpterdate.MyViewHolder> {
    private static Context context;
    private Activity activity;
    private ArrayList<String> dates;

    public Adpterdate(Activity activity, Context context, ArrayList<String> dates) {
        this.activity = activity;
        this.context = context;
        this.dates = dates;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_adpterdate, parent, false);
        return new MyViewHolder(view);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        String currentDate = dates.get(position);
        holder.date.setText(currentDate);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DateView.class);
                intent.putExtra("Date", String.valueOf(dates.get(position)));

                activity.startActivityForResult(intent, 1);
                activity.overridePendingTransition(R.anim.selectpage,R.anim.selectpagee);
            }
        });

    }


    @Override
    public int getItemCount() {
        return dates.size();
    }

    TextView date;
    static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout mainLayout;
        TextView date;

        MyViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_View);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}