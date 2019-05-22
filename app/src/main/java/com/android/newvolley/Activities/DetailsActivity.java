package com.android.newvolley.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.newvolley.Classes.PicassoTrustAll;
import com.android.newvolley.R;
import com.android.newvolley.ViewHolder.ReviewsAdapter;

import static com.android.newvolley.ViewHolder.Adapter.reviewsArrayList;

public class DetailsActivity extends AppCompatActivity {
    ListView mList;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView textViewname = findViewById(R.id.name_txt);
        TextView textViewPrice = findViewById(R.id.price_txt);
        ImageView imageView = findViewById(R.id.imageView);
        mList = findViewById(R.id.list);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int price = intent.getIntExtra("price", 0);
        String image = intent.getStringExtra("image");
        ReviewsAdapter reviewsAdapter = new ReviewsAdapter(this,R.layout.list_item,reviewsArrayList);
        mList.setAdapter(reviewsAdapter);
        textViewname.setText(name);
        textViewPrice.setText(Integer.toString(price));
        PicassoTrustAll.getInstance(this).load(image).into(imageView);
    }
}
