package com.majorproject.chumlung.bottomtabbed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ScrollView;
import android.widget.TextView;

public class SingleBlog extends AppCompatActivity {

    TextView title,blog,date;
    String intitle,inblog,indate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinlge_blog);
        Intent i = getIntent();
        intitle = i.getStringExtra("sel_title");
        indate = i.getStringExtra("sel_date");
        inblog = i.getStringExtra("sel_blog");


        title = (TextView)findViewById(R.id.single_blog_title);
        blog = (TextView)findViewById(R.id.single_blog);
        date = (TextView)findViewById(R.id.single_date);

        title.setText(intitle);
        date.setText(indate);
        blog.setText(inblog);


    }
}
