package com.example.bookalarm.USERSET;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.bookalarm.LOGIN.LoginPage;
import com.example.bookalarm.R;
import com.example.bookalarm.USERDB.SecRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class UserPage extends AppCompatActivity {
    Button logOut_user, profile_user, secession_user;
    Calendar cal = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_page);

        profile_user = findViewById(R.id.profile_user);
        logOut_user = findViewById(R.id.logOut_user);
        secession_user = findViewById(R.id.secession_user);

        profile_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(UserPage.this);
                ad.setTitle("알림");
                ad.setMessage("비밀번호를 입력해주세요.");

                final EditText et = new EditText(UserPage.this);

                ad.setView(et);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userPWD = et.getText().toString();

                        if(userPWD.length() == 0){
                            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        else{
                            dialog.dismiss();
                            Intent intent = new Intent(UserPage.this, UserModify.class);
                            startActivity(intent);
                        }
                        dialog.dismiss();
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ad.show();
            }
        });
        logOut_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserPage.this, LoginPage.class);
                startActivity(intent);
                finish();
            }
        });
        secession_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(UserPage.this);
                ad.setTitle("알림");
                ad.setMessage("탈퇴하시려면 번호를 입력해주세요");

                final EditText et = new EditText(UserPage.this);
                ad.setView(et);

                ad.setPositiveButton("탈퇴", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String userCell = et.getText().toString();

                        if(userCell.length() == 0){
                            Toast.makeText(getApplicationContext(), "번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Log.d("xogud","user1");
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    Log.d("xogud","user2");

                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean success = jsonObject.getBoolean("success");
                                    Log.d("xogud","user3");

                                    if(success){
                                        Log.d("xogud","user4");

                                        Toast.makeText(getApplicationContext(),"탈퇴 성공.",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UserPage.this, LoginPage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Log.d("xogud","user5");

                                        Toast.makeText(getApplicationContext(),"탈퇴 실패.",Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                                catch (JSONException e){
                                    Log.d("xogud","user6");

                                    e.printStackTrace();
                                }
                            }
                        };
                        Log.d("xogud","user7");

                        SecRequest secRequest = new SecRequest(userCell, responseListener);
                        RequestQueue requestQueue = Volley.newRequestQueue(UserPage.this);
                        requestQueue.add(secRequest);
                        Log.d("xogud","user8");

                        dialog.dismiss();
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                ad.show();

            }
        });
        /*
        secession_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("xogud019","error");
                String secDate = "";
                String extDate = "";
                String userCell = "";
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH) + 1;
                int day = cal.get(Calendar.DAY_OF_MONTH);

                secDate = year+""+month+""+day;

                if(day+1>30) extDate = year+""+month+""+0+""+1;
                else extDate = year+""+month+""+(day+1);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success){
                                Toast.makeText(getApplicationContext(),"탈퇴 성공.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserPage.this, LoginPage.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"탈퇴 실패.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                SecRequest secRequest = new SecRequest(userCell, secDate, extDate, responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(UserPage.this);
                requestQueue.add(secRequest);
            }
        });

         */
    }
}