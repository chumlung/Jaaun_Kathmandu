package com.majorproject.chumlung.bottomtabbed;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;
import static com.majorproject.chumlung.bottomtabbed.AppConstants.hListJson;

/**
 * Created by Chumlung on 8/2/2017.
 **/

public class BackgroundWork extends AsyncTask<String,Void,String> {
    Context context;
    private CallBack listener;
    AlertDialog alertDialog;
    ProgressDialog progressDialog;



    BackgroundWork(Context ctx, CallBack listener) {
        context = ctx;

        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[2];
        String login_url = AppConstants.IP_SET + "androidLogin.php";
        if (type.equals("login")) {
            try {
                String uname = params[0];
                String pass = params[1];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(uname, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
        progressDialog= new ProgressDialog(context);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    @Override
    protected void onPostExecute(String aVoid) {
        alertDialog.setMessage(aVoid);
        int result = Integer.parseInt(aVoid);


        if (result != 0) {
            AppConstants.loggedid = result;
            listener.onSuccess();


            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
            LoginPrefManager.getInstance(context).putLoginUID(Integer.toString(result));

            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }



        } else {
            Toast.makeText(context, "Failed to Login", Toast.LENGTH_SHORT).show();
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
