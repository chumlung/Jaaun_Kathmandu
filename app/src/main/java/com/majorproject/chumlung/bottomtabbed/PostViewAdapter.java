package com.majorproject.chumlung.bottomtabbed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chumlung on 8/11/2017.
 */

public class PostViewAdapter extends BaseAdapter{
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<>();


    public PostViewAdapter(Context context,
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


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_post, parent, false);
        // Get the position
        resultp = data.get(position);
// Locate the TextViews in listview_item.xml
        name =  itemView.findViewById(R.id.userposts);



        name.setText(resultp.get("post"));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {}
           // Get the position
        });





                return itemView;
    }
}
