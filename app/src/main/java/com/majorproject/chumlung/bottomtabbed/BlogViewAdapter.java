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

public class BlogViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<>();


    public BlogViewAdapter(Context context,
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
        TextView name,date;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_blog, parent, false);
        // Get the position
        resultp = data.get(position);
// Locate the TextViews in listview_item.xml
        name =  itemView.findViewById(R.id.blog_title);
// Locate the ImageView in listview_item.xml
        date =  itemView.findViewById(R.id.blog_date);




        name.setText(resultp.get("title"));
        date.setText(resultp.get("created_date"));




        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);
                Intent intent = new Intent(context, SingleBlog.class);

                intent.putExtra("sel_title", resultp.get("title"));
                intent.putExtra("sel_date",resultp.get("created_date"));
                intent.putExtra("sel_blog",resultp.get("blog"));


                context.startActivity(intent);


            }
        });
        return itemView;
    }
}
