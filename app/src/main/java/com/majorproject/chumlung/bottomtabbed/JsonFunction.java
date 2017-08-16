package com.majorproject.chumlung.bottomtabbed;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;
import static com.majorproject.chumlung.bottomtabbed.AppConstants.blogListJson;
import static com.majorproject.chumlung.bottomtabbed.AppConstants.hListJson;
import static com.majorproject.chumlung.bottomtabbed.AppConstants.restListJson;
import static com.majorproject.chumlung.bottomtabbed.AppConstants.userPostList;

g/**
 * Created by Chumlung on 8/10/2017.
 */

public class JsonFunction extends  AsyncTask<String, Void, String > {
    public String listurl = AppConstants.IP_SET + "androidHotelRec.php";
    public String listresturl = AppConstants.IP_SET + "androidRestaurantRec.php";
    public String listblogurl =  AppConstants.IP_SET+"androidblog.php";
    public String listuserurl = AppConstants.IP_SET+"androidUser.php";


    ProgressDialog progressDialog;
    Context context;


    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Getting Recommendations...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        // Showing progress dialog

    }

    @Override
    protected String doInBackground(String... params) {
        HttpHandler httpHandler = new HttpHandler();
        String jsonStr = httpHandler.makeServiceCall(listurl);
        HttpHandler resthttpHandler = new HttpHandler();
        String jsonRestStr = resthttpHandler.makeServiceCall(listresturl);
        HttpHandler blogHandler = new HttpHandler();
        String jsonBlogStr = blogHandler.makeServiceCall(listblogurl);
        HttpHandler userHandler = new HttpHandler();
        String jsonUserStr = userHandler.makeServiceCall(listuserurl);




        hListJson = new ArrayList<>();
        restListJson = new ArrayList<>();
        blogListJson = new ArrayList<>();
        userPostList = new ArrayList<>();

        Log.e(TAG, "Response from url:" + jsonStr);


        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray hotels = jsonObj.getJSONArray("hotel");


                // looping through hotel elements
                for (int i = 0; i < hotels.length(); i++) {
                    JSONObject c = hotels.getJSONObject(i);

                    String hotel_name = c.getString("hotel_name");
                    String hotel_img = c.getString("hotel_img");
                    String hotel_id = c.getString("hotel_id");
                    String hotel_location = c.getString("hotel_location");
                    String hotel_rating = c.getString("rating");

                    // tmp hash map for single hotel details
                    HashMap<String, String> hotelsmap = new HashMap<>();

                    // adding each child node to HashMap key => value
                    hotelsmap.put("hotel_name", hotel_name);
                    hotelsmap.put("hotel_img", hotel_img);
                    hotelsmap.put("hotel_id", hotel_id);
                    hotelsmap.put("hotel_location", hotel_location);
                    hotelsmap.put("hotel_rating", hotel_rating);

                    // adding hotels to hotel list
                    hListJson.add(hotelsmap);
                }

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }



        if (jsonRestStr != null) {
            try {

                JSONObject resJson = new JSONObject(jsonRestStr);
                JSONArray restaurants = resJson.getJSONArray("restaurant");
                // looping through All restaurants

                for (int i = 0; i < restaurants.length(); i++) {
                    JSONObject c = restaurants.getJSONObject(i);

                    String restaurant_name = c.getString("restaurant_name");
                    String restaurant_image = c.getString("restaurant_image");
                    String restaurant_id = c.getString("restaurant_id");
                    String restaurant_location = c.getString("restaurant_location");
                    String rating = c.getString("rating");

                    // tmp hash map for single restaurant
                    HashMap<String, String> restsmap = new HashMap<>();

                    // adding each child node to HashMap key => value
                    restsmap.put("restaurant_name", restaurant_name);
                    restsmap.put("restaurant_image", restaurant_image);
                    restsmap.put("restaurant_id", restaurant_id);
                    restsmap.put("restaurant_location", restaurant_location);
                    restsmap.put("rating", rating);

                    // adding restaurant to restaurant list
                    restListJson.add(restsmap);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }


        if (jsonBlogStr != null) {
            try {
                JSONObject jsonBlogObj = new JSONObject(jsonBlogStr);

                // Getting JSON Array node
                JSONArray blogs = jsonBlogObj.getJSONArray("blogs");

                // looping through All Blogs
                for (int i = 0; i < blogs.length(); i++) {
                    JSONObject c = blogs.getJSONObject(i);

                    String blog = c.getString("blog");
                    String title = c.getString("title");
                    String image = c.getString("image");
                    String created_by = c.getString("created_by");
                    String created_date = c.getString("created_date");
                    String category = c.getString("category");
                    String blog_id = c.getString("blog_id");

                    // tmp hash map for single blog
                    HashMap<String, String> blogsmap = new HashMap<>();

                    // adding each child node to HashMap key => value
                    blogsmap.put("blog", blog);
                    blogsmap.put("title", title);
                    blogsmap.put("image", image);
                    blogsmap.put("created_by", created_by);
                    blogsmap.put("category",category);
                    blogsmap.put("created_date", created_date);
                    blogsmap.put("blog_id",blog_id);

                    // adding blog to bloglist
                    blogListJson.add(blogsmap);
                }

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: in blog " + e.getMessage());

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        if (jsonUserStr != null) {
            try {
                JSONObject jsonuserObj = new JSONObject(jsonUserStr);

                // Getting JSON Array node
                JSONArray users = jsonuserObj.getJSONArray("user");


                // looping through All users
                for (int i = 0; i < users.length(); i++) {
                    JSONObject c = users.getJSONObject(i);

                    String user_photo = c.getString("user_photo");
                    String Email = c.getString("Email");
                    String user_biography = c.getString("user_biography");
                    String post = c.getString("post");
                    String created_date = c.getString("created_date");

                    // tmp hash map for single user
                    HashMap<String, String> usersmap = new HashMap<>();

                    // adding each child node to HashMap key => value
                    usersmap.put("user_photo", user_photo);
                    usersmap.put("Email", Email);
                    usersmap.put("user_biography", user_biography);
                    usersmap.put("post", post);
                    usersmap.put("created_date", created_date);

                    // adding users to userpostlist
                    userPostList.add(usersmap);
                }

            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error in posts: " + e.getMessage());

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }





        return "complete";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equalsIgnoreCase("complete")) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
        // Dismiss the progress dialog


    }

    public JsonFunction(Context c){
        context=c;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
