package com.example.bookalarm.LOGIN;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.bookalarm.R;

public class Search extends Activity {
    EditText searchIDCell, searchPWId, searchPWCell;
    String tSearchIDCell, tSearchPWId, tSearchPWCell;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchIDCell = (EditText) findViewById(R.id.SearchIDCell); // 아이디 찾기의 이름 입력 란
        searchPWId = (EditText) findViewById(R.id.SearchPWId); // 비밀번호 찾기의 아이디 입력 란
        searchPWCell = (EditText) findViewById(R.id.SearchPWCell); // 비밀번호 찾기의 이름 입력 란
        Button searchIDbtn = (Button) findViewById(R.id.SearchIDbtn);
        Button searchPWbtn = (Button) findViewById(R.id.SearchPWbtn);
    }

}