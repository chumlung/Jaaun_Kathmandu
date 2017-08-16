package com.majorproject.chumlung.bottomtabbed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chumlung on 8/11/2017.
 */

public class ListViewAdapterRests extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<>();

    public ListViewAdapterRests(Context context,
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

        TextView name;
        ImageView image;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item, parent, false);

        resultp = data.get(position);

        name =  itemView.findViewById(R.id.list_name);

        image =  itemView.findViewById(R.id.list_image);


        String image_src;
        String imageuri="";


            name.setText(resultp.get("restaurant_name"));
            image_src = resultp.get("restaurant_image");
            imageuri = AppConstants.IP_SET + "/img/restaurants/" + image_src;




        Picasso.with(context).load(imageuri).placeholder(R.drawable.temp_img).resize(450,300).into(image);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleItemRest.class);

                intent.putExtra("name", resultp.get("restaurant_name"));
                intent.putExtra("url",resultp.get("restaurant_image"));
                intent.putExtra("location",resultp.get("restaurant_location"));
                intent.putExtra("rating",resultp.get("rating"));


                context.startActivity(intent);


            }
        });
        return itemView;
    }
}
