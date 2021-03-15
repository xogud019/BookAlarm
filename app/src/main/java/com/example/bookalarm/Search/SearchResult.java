package com.example.bookalarm.Search;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.bookalarm.DB.DBHelper;
import com.example.bookalarm.Data.ListItem;
import com.example.bookalarm.MainPage.Main;
import com.example.bookalarm.R;

public class SearchResult extends AppCompatActivity {
    TextView title_res,price_res,salePrice_res,disc_res,pub_res,pubD_res,sale_res,aut_res,trs_res,dec_res;
    Button reg_res;
    ImageView iv_res;
    DBHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_result);

        Intent intent = getIntent();
        ListItem listItem =  (ListItem)intent.getSerializableExtra("obj");

        //view 생성
        title_res = findViewById(R.id.title_res);
        price_res = findViewById(R.id.price_res);
        salePrice_res = findViewById(R.id.salePrice_res);
        disc_res= findViewById(R.id.disc_res);
        //rate_res= findViewById(R.id.rate_res);
        pub_res= findViewById(R.id.pub_res);
        pubD_res= findViewById(R.id.pubD_res);
        sale_res= findViewById(R.id.sale_res);
        aut_res= findViewById(R.id.aut_res);
        trs_res= findViewById(R.id.trs_res);
        dec_res= findViewById(R.id.dec_res);
        reg_res = findViewById(R.id.reg_res);
        iv_res = findViewById(R.id.iv_res);
        //

        //setText
        title_res.setText(listItem.getTitle());
        price_res.setText(listItem.getPriceStandard());
        salePrice_res.setText(listItem.getPriceSales());
        disc_res.setText(listItem.getDiscountRate());
        //rate_res.setText(listItem.getRank());
        pub_res.setText(listItem.getPublisher());
        pubD_res.setText(listItem.getPubDate());
        sale_res.setText(listItem.getSaleStatus());
        aut_res.setText(listItem.getAuthor());
        trs_res.setText(listItem.getTranslator());
        dec_res.setText(listItem.getDescription());
        Glide.with(this).load(listItem.getCoverLargeUrl()).into(iv_res);

        reg_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DBHelper(SearchResult.this, dbHelper.DATABASE_NAME,null,1);
                db = dbHelper.getWritableDatabase();
                boolean insert = dbHelper.insertData(title_res.getText().toString(),pub_res.getText().toString(),aut_res.getText().toString(),
                        pubD_res.getText().toString(), listItem.getCoverSmallUrl().toString(),price_res.getText().toString(), salePrice_res.getText().toString());

                if(insert){
                    Toast.makeText(getApplicationContext(),"등록 완료",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SearchResult.this, Main.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"등록 실패",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }
}