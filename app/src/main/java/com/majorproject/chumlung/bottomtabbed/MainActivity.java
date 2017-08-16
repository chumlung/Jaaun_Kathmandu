package com.majorproject.chumlung.bottomtabbed;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.majorproject.chumlung.bottomtabbed.AppConstants.blogListJson;
import static com.majorproject.chumlung.bottomtabbed.AppConstants.hListJson;
import static com.majorproject.chumlung.bottomtabbed.AppConstants.userPostList;

public class MainActivity extends AppCompatActivity {

    ListView hotel;
    ListView rests;
    ImageView userimage;
    ListView userpostslist;
    ListView bloglist;
    TextView userbio;
    TextView welcome;
    TextView biolabel;
    TextView postlabel;
    TextView empty;
    Button logout;
    String imgurl;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_hotel:


                    ListViewAdapter adapter = new ListViewAdapter(MainActivity.this,hListJson);

                    //visibility settings
                    welcome.setText("Recommmended Hotels");

                    hotel.setAdapter(adapter);

                    userimage.setVisibility(View.INVISIBLE);
                    userbio.setVisibility(View.INVISIBLE);
                    userpostslist.setVisibility(View.INVISIBLE);
                    bloglist.setVisibility(View.INVISIBLE);
                    empty.setVisibility(View.INVISIBLE);

                    biolabel.setVisibility(View.INVISIBLE);
                    postlabel.setVisibility(View.INVISIBLE);


                    hotel.setVisibility(View.VISIBLE);
                    rests.setVisibility(View.INVISIBLE);
                    logout.setVisibility(View.INVISIBLE);

                    return true;
                case R.id.navigation_home:
                    welcome.setText("Namaste "+AppConstants.uname);
                    welcome.setVisibility(View.VISIBLE);
                    String imgname= userPostList.get(0).get("user_photo");
                    imgurl = AppConstants.IP_SET+"/img/users/" + imgname;
                    Picasso.with(MainActivity.this).load(imgurl).placeholder(R.drawable.temp_img).resize(200,200).into(userimage);
                    userbio.setText(userPostList.get(0).get("user_biography"));

                    PostViewAdapter postadapter = new PostViewAdapter(MainActivity.this,userPostList);
                    userpostslist.setAdapter(postadapter);

                    userimage.setVisibility(View.VISIBLE);
                    userbio.setVisibility(View.VISIBLE);
                    userpostslist.setVisibility(View.VISIBLE);
                    empty.setVisibility(View.INVISIBLE);

                    biolabel.setVisibility(View.VISIBLE);
                    postlabel.setVisibility(View.VISIBLE);

                    bloglist.setVisibility(View.INVISIBLE);
                    hotel.setVisibility(View.INVISIBLE);
                    rests.setVisibility(View.INVISIBLE);
                    logout.setVisibility(View.VISIBLE);
                    bloglist.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_blogs:

                    if(blogListJson.isEmpty()){
                        empty.setText("No blogs yet");
                        empty.setVisibility(View.VISIBLE);
                    }
                    else
                        {
                        welcome.setText("Your blogs");
                        BlogViewAdapter blogadapter = new BlogViewAdapter(MainActivity.this, blogListJson);
                        bloglist.setAdapter(blogadapter);


                        userimage.setVisibility(View.INVISIBLE);
                        userbio.setVisibility(View.INVISIBLE);
                        userpostslist.setVisibility(View.INVISIBLE);
                            empty.setVisibility(View.INVISIBLE);

                        bloglist.setVisibility(View.VISIBLE);

                        biolabel.setVisibility(View.INVISIBLE);
                        postlabel.setVisibility(View.INVISIBLE);

                        hotel.setVisibility(View.INVISIBLE);
                        rests.setVisibility(View.INVISIBLE);
                        logout.setVisibility(View.INVISIBLE);
                    }


                    return true;
                case R.id.navigation_places:
                    ListViewAdapterRests restadapter = new ListViewAdapterRests(MainActivity.this,AppConstants.restListJson);
                    rests.setAdapter(restadapter);

                    //visibility settings
                    welcome.setText("Recommended Restaurants");

                    userimage.setVisibility(View.INVISIBLE);
                    userbio.setVisibility(View.INVISIBLE);
                    userpostslist.setVisibility(View.INVISIBLE);
                    empty.setVisibility(View.INVISIBLE);

                    biolabel.setVisibility(View.INVISIBLE);
                    postlabel.setVisibility(View.INVISIBLE);

                    bloglist.setVisibility(View.INVISIBLE);
                    hotel.setVisibility(View.INVISIBLE);
                    rests.setVisibility(View.VISIBLE);
                    logout.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.drawable.logowjkldpi);

        JsonFunction getHotelsJson = new JsonFunction(MainActivity.this);
        getHotelsJson.execute();
        hotel = (ListView)findViewById(R.id.hotel_list);
        rests = (ListView)findViewById(R.id.rest_list);
        welcome = (TextView) findViewById(R.id.welcome);
        userimage = (ImageView)findViewById(R.id.userimage);
        userbio = (TextView)findViewById(R.id.userbio);
        userpostslist = (ListView)findViewById(R.id.userpost);
        bloglist = (ListView)findViewById(R.id.bloglistview);
        biolabel =(TextView)findViewById(R.id.biolabel);
        postlabel = (TextView)findViewById(R.id.postlabel);
        empty=(TextView)findViewById(R.id.oops);




        logout = (Button) findViewById(R.id.logoutbtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginPrefManager.getInstance(getApplicationContext()).ClearLogin();
                Intent j=new Intent(MainActivity.this,Login.class);
                startActivity(j);
                finish();

            }
        });
        logout.setVisibility(View.VISIBLE);
        welcome.setText("Namaste "+AppConstants.uname);
        welcome.setVisibility(View.VISIBLE);

        LoginPrefManager.getInstance(getApplicationContext()).putLoginStatus("true");




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }




}
