package com.example.bookalarm.LOGIN;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.bookalarm.DB.DBHelper;
import com.example.bookalarm.MainPage.Main;
import com.example.bookalarm.R;
import com.example.bookalarm.USERDB.LoginRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginPage extends Activity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    Button btnLogin;
    TextView btnSignup, btnSearch;
    EditText loginId, loginPw;
    String id, pw, Logout_Code;
    CheckBox checkBox;
    Intent intent;
    Cursor cursor;
    SharedPreferences autoLogin;
    long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);

        loginId = findViewById(R.id.LoginId);
        loginPw = findViewById(R.id.LoginPw);

        btnLogin = findViewById(R.id.login);
        btnSignup = findViewById(R.id.signup);
        btnSearch = findViewById(R.id.search);
        checkBox = findViewById(R.id.autoCheck);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = loginId.getText().toString();
                String pwd = loginPw.getText().toString();

                if(id.length() == 0 || pwd.length() == 0){ // 아이디 또는 비밀번호가 입력이 안된 경우
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success){
                                Toast.makeText(getApplicationContext(),"로그인 성공.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginPage.this, Main.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"로그인 실패.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest logRequest = new LoginRequest(id, pwd, responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginPage.this);
                requestQueue.add(logRequest);
            }
        });
        /*

        */
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, SignUp.class);
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, Search.class);
                startActivity(intent);
            }
        });
        /*
        autoLogin = getSharedPreferences("autoLogin", 0);
        editor = autoLogin.edit();

        intent = getIntent();
        Logout_Code = intent.getStringExtra("Logout_Code");
        Log.d("xogud", Logout_Code);

        if(autoLogin.getBoolean("chk_auto", false)){


            loginId.setText(autoLogin.getString("ID", ""));
            loginPw.setText(autoLogin.getString("PW", ""));

            sloginId = autoLogin.getString("ID","");
            Log.d("xogud", sloginId);

            checkBox.setChecked(true);

            if(Logout_Code.equals("f")){
                intent = new Intent(this, Main.class);
                startActivity(intent); // 앱 실행 시 로그인 없이 메인 액티비티로 이동
                finish();
            }

            else{ // 매인 액티비티에서 로그아웃 할 때
                loginId.setText(autoLogin.getString("", ""));
                loginPw.setText(autoLogin.getString("", ""));
                checkBox.setChecked(false);

                editor.clear();
                editor.commit();
                return;

            }

        }
        */

    }
    @Override
    public void onBackPressed(){
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0<= gapTime && 2000 >= gapTime) super.onBackPressed();
        else{
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }

}