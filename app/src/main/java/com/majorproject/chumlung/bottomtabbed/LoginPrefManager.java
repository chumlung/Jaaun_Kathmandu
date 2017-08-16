package com.majorproject.chumlung.bottomtabbed;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * * Created by Chumlung on 8/2/2017.
 */

public class LoginPrefManager {
    SharedPreferences sharedPreferences;
    public static final String MyLoginPref = "MyPrefs" ;
    public static final String STATUS = "statusKey";
    public static final String username = "userKey" ;
    public static final String password = "passKey" ;
    public static final String UID = "useridKey";
    public static final String IpAdd ="";
    Context Ctx;

    private static LoginPrefManager mInstance;

    private LoginPrefManager(Context context) {
        Ctx = context;
    }

    public static synchronized LoginPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LoginPrefManager(context);
        }
        return mInstance;
    }

    //this method will save the device token to shared preferences
    public void putLoginStatus(String status){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(STATUS, status);
        editor.apply();
    }
    public void putLoginID(String id){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, id);
        editor.apply();
    }
    public void putLoginPass(String pass){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(password, pass);
        editor.apply();
    }
    public void putLoginUID(String uid){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(UID, uid);
        editor.apply();
    }
    public void putLoginIpAdd(String ip) {
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(IpAdd, ip);
        editor.apply();
    }



    //this method will fetch the device login info from shared preferences
    public String getLoginStatus(){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(STATUS, "false");
    }


    public String getLoginID(){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(username,"");
    }

    public String getLoginPass(){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(password,"");
    }
    public String getLoginUID(){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(UID,"");
    }
    public String getLoginIpAdd(){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(IpAdd,"");
    }


    public void ClearLogin(){
        SharedPreferences sharedPreferences = Ctx.getSharedPreferences(MyLoginPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
