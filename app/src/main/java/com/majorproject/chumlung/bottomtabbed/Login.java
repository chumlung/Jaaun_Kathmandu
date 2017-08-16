package com.majorproject.chumlung.bottomtabbed;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity implements CallBack{
        EditText userName,passw,ipadd;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.drawable.logowjkldpi);


            setContentView(R.layout.activity_login);
            userName = (EditText) findViewById(R.id.user_name);
            passw =(EditText) findViewById(R.id.Password);
            ipadd =(EditText) findViewById(R.id.ipadd);



            Button loginbtn = (Button)findViewById(R.id.login);
            loginbtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    AppConstants.uname = userName.getText().toString();
                    String u_Name = userName.getText().toString();
                    String p_Word = passw.getText().toString();
                    AppConstants.IP_SET="http://"+ipadd.getText().toString()+"/Website/";
                    String type= "login";

                    LoginPrefManager.getInstance(getApplicationContext()).putLoginID(u_Name);
                    LoginPrefManager.getInstance(getApplicationContext()).putLoginPass(p_Word);
                    LoginPrefManager.getInstance(getApplicationContext()).putLoginIpAdd(ipadd.getText().toString());


                    execute(u_Name,p_Word,type);





                }
            });

            String check = LoginPrefManager.getInstance(getApplicationContext()).getLoginStatus();
            if(check.equals("true")){
                AppConstants.uname = LoginPrefManager.getInstance(getApplicationContext()).getLoginID();
                AppConstants.password = LoginPrefManager.getInstance(getApplicationContext()).getLoginPass();
                AppConstants.loggedid = Integer.parseInt(LoginPrefManager.getInstance(getApplicationContext()).getLoginUID());
                AppConstants.IP_SET = "http://"+LoginPrefManager.getInstance(getApplicationContext()).getLoginIpAdd()+"/Website/";
                System.out.println("new app constant logged id"+AppConstants.loggedid);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    private void execute(String uname,String pass,String type) {
        BackgroundWork backgroundWork = new BackgroundWork(Login.this,this);
        backgroundWork.execute(uname, pass, type);
    }
    @Override
    public void onSuccess() {
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}

