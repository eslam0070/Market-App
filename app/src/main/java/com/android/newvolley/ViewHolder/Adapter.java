package com.android.newvolley.ViewHolder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.newvolley.Activities.DetailsActivity;
import com.android.newvolley.Models.Data;
import com.android.newvolley.Models.Reviews;
import com.android.newvolley.Classes.PicassoTrustAll;
import com.android.newvolley.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewProcessHolder> {

    private Context context;
    public List<Data> item;
    public static ArrayList<Reviews> reviewsArrayList ;

    public Adapter(Context context, List<Data> item) {
        this.context = context;
        this.item = item;

    }



    @NonNull
    @Override
    public Adapter.ViewProcessHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view,
                viewGroup, false);
        return new ViewProcessHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewProcessHolder viewProcessHolder, final int i) {
        final Data data = item.get(i);
        viewProcessHolder.textViewTitle.setText(data.getName());
        viewProcessHolder.textViewPrice.setText(Integer.toString(data.getPrice()));
        PicassoTrustAll.getInstance(context).load(data.getImgUrl()).into(viewProcessHolder.imageView);

        viewProcessHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("name", data.getName());
                intent.putExtra("price",data.getPrice());
                intent.putExtra("image",data.getImgUrl());
                reviewsArrayList = data.getReviewsArrayList();
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class ViewProcessHolder extends RecyclerView.ViewHolder
    {

        TextView textViewTitle,textViewPrice;
        ImageView imageView;
        RelativeLayout relativeLayout;
        RatingBar ratingBar;




        ViewProcessHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewname);
            textViewPrice = itemView.findViewById(R.id.textViewprive);
            imageView =  itemView.findViewById(R.id.imageView);
            ratingBar = itemView.findViewById(R.id.rating);
            relativeLayout = itemView.findViewById(R.id.Relative);

        }
    }
}
