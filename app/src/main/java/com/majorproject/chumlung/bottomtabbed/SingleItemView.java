package com.majorproject.chumlung.bottomtabbed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SingleItemView extends AppCompatActivity {

    String name,imagesrc,location,rate;
    TextView svn,svloc;
    RatingBar ratingBar;
    ImageView imageView;
    String imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item_view);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        imagesrc=i.getStringExtra("url");



        imgurl = AppConstants.IP_SET+"/img/hotels/"+imagesrc;

        location=i.getStringExtra("location");
        rate=i.getStringExtra("rating");

        svn = (TextView)findViewById(R.id.svname);
        imageView = (ImageView)findViewById(R.id.svimage);
        svloc = (TextView)findViewById(R.id.svlocation);
        ratingBar = (RatingBar)findViewById(R.id.ratings);



        svn.setText(name);
        svloc.setText(location);
        ratingBar.setRating(Float.parseFloat(rate));
        Picasso.with(SingleItemView.this).load(imgurl).placeholder(R.drawable.temp_img).resize(450,300).into(imageView);
    }
}
