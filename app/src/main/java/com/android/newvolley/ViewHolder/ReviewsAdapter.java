package com.android.newvolley.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.newvolley.Models.Reviews;
import com.android.newvolley.R;

import java.util.ArrayList;
public class ReviewsAdapter extends ArrayAdapter<Reviews> {


    public ReviewsAdapter(Context context, int resource, ArrayList<Reviews> objects) {
        super(context, resource, objects);
    }


    @SuppressLint({"ViewHolder", "SetTextI18n"})
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item , parent ,false);

        TextView textView = convertView.findViewById(R.id.textView5);
        TextView textView1 = convertView.findViewById(R.id.textView6) ;

        textView.setText((getItem(position)).getUsername());
        textView1.setText(""+ getItem(position).getRate());

        return convertView;
    }
}
