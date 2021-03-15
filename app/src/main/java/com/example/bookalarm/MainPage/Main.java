package com.example.bookalarm.MainPage;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.bookalarm.DB.DBHelper;
import com.example.bookalarm.R;
import com.example.bookalarm.SETTING.Setting;
import com.example.bookalarm.Search.SearchMain;
import com.example.bookalarm.USERSET.UserPage;
import com.google.android.material.tabs.TabLayout;

public class Main extends AppCompatActivity {

    private FragmentPagerAdapter fragmentPagerAdapter;
    SQLiteDatabase db;
    ViewPager vp_main;
    TabLayout tl_main;
    Button plus_main, minus_main;
    ImageButton user_main, set_main;
    DBHelper dbHelper;
    long backBtnTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(Main.this,dbHelper.DATABASE_NAME, null,1);

        user_main = findViewById(R.id.user_main);
        set_main = findViewById(R.id.set_main);
        plus_main = findViewById(R.id.plus_main);
        minus_main = findViewById(R.id.minus_main);
        vp_main = findViewById(R.id.vp_main);
        fragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        tl_main = findViewById(R.id.tl_main);
        vp_main.setAdapter(fragmentPagerAdapter);
        tl_main.setupWithViewPager(vp_main);

        plus_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, SearchMain.class);
                startActivity(intent);
            }
        });

        minus_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main.this, "삭제할 항목을 선택해 주세요.", Toast.LENGTH_SHORT).show();
            }
        });

        set_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Setting.class);
                startActivity(intent);
            }
        });

        user_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, UserPage.class);
                startActivity(intent);
            }
        });
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
