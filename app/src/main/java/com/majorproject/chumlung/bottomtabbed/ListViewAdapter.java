package com.majorproject.chumlung.bottomtabbed;

/**
 * Created by Chumlung on 8/10/2017.
 */

import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

        // Declare Variables
        Context context;
        LayoutInflater inflater;
        ArrayList<HashMap<String, String>> data;
        HashMap<String, String> resultp = new HashMap<>();
        String viewFor;

        public ListViewAdapter(Context context,
                               ArrayList<HashMap<String, String>> arraylist) {
            this.context = context;
            data = arraylist;

        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            // Declare Variables
            TextView name;
            ImageView image;


            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemView = inflater.inflate(R.layout.list_item, parent, false);
            // Get the position
            resultp = data.get(position);
// Locate the TextViews in listview_item.xml
            name =  itemView.findViewById(R.id.list_name);
// Locate the ImageView in listview_item.xml
            image =  itemView.findViewById(R.id.list_image);

            // Capture position and set results to the TextViews
            String image_src;
            String imageuri="";



            name.setText(resultp.get("hotel_name"));
            image_src = resultp.get("hotel_img");
            imageuri = AppConstants.IP_SET + "/img/hotels/" + image_src;



            Picasso.with(context).load(imageuri).placeholder(R.drawable.temp_img).resize(450,300).into(image);

            // Capture ListView item click
           itemView.setOnClickListener(new OnClickListener() {
               @Override
                public void onClick(View arg0) {
                    // Get the position
                    resultp = data.get(position);
                    Intent intent = new Intent(context, SingleItemView.class);

                   intent.putExtra("name", resultp.get("hotel_name"));
                   intent.putExtra("url",resultp.get("hotel_img"));
                   intent.putExtra("location",resultp.get("hotel_location"));
                   intent.putExtra("rating",resultp.get("hotel_rating"));


                   context.startActivity(intent);


                }
            });
            return itemView;
        }
}

