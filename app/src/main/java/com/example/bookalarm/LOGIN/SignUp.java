package com.example.bookalarm.LOGIN;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.bookalarm.POPUP.PopUp1;
import com.example.bookalarm.POPUP.PopUp2;
import com.example.bookalarm.R;
import com.example.bookalarm.USERDB.RegRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends Activity{
    SQLiteDatabase database;
    EditText idSign, pwSign, pwSign2, cellPw;
    String tIdSign, tPwSign, tPwSign2, tCellNum;
    CheckBox inform_check;
    Intent intent;
    Cursor cursor;

    boolean pwCheck;
    Spinner spinner;
    ArrayAdapter adapterSpinner;

    private final String closePopup_1 = "Close Popup_1"; // 이용약관 팝업
    private final String closePopup_2 = "Close Popup_2"; // 정보제공 팝업
    String result_1, result_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);
        //database = Database.getInstance().open(this);
        //Log.d("xogud", "데이터베이스 사용 가능");

        idSign = (EditText) findViewById(R.id.idSign);
        pwSign = (EditText) findViewById(R.id.pwSign);
        pwSign2 = (EditText) findViewById(R.id.pwSign2);
        cellPw = (EditText) findViewById(R.id.cellPw);
        inform_check = (CheckBox) findViewById(R.id.inform_check); // 이용약관 및 정보 동의
        inform_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result_1 == null || result_2 == null){ // 내용 미 확인 후 동의 체크 시
                    inform_check.setChecked(false);
                    Toast.makeText(SignUp.this, "이용약관 및 개인정보정책 내용을 \n확인해주세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button joinBtn = (Button) findViewById(R.id.Joinbtn); // 회원가입
        Button watchBtn1 = (Button) findViewById(R.id.watch_btn1); // 이용약관 보기
        Button watchBtn2 = (Button) findViewById(R.id.watch_btn2); // 개인정보제공 보기

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idSign.getText().toString();
                String pwd = pwSign.getText().toString();
                String pwd2 = pwSign.getText().toString();
                String userCell = cellPw.getText().toString();

                if(!pwd.equals(pwd2)){
                    Toast.makeText(getApplicationContext(),"비밀번호가 다릅니다",Toast.LENGTH_SHORT).show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if(success){
                                Toast.makeText(getApplicationContext(),"회원 가입을 완료했습니다.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, LoginPage.class);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"회원 가입에 실패했습니다.",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                RegRequest regRequest = new RegRequest(id, pwd, userCell, responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(SignUp.this);
                requestQueue.add(regRequest);
            }
        });
        /*
        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tIdSign = idSign.getText().toString();
                tPwSign = pwSign.getText().toString();
                tPwSign2 = pwSign2.getText().toString();
                tCellNum = cellPw.getText().toString();
                pwCheck = Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{6,16}$", tPwSign);

                if(tIdSign.trim().length() == 0 || tPwSign.trim().length() == 0 || tPwSign2.trim().length() == 0 || tCellNum.trim().length() == 0){
                    Toast.makeText(SignUp.this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("xogud", "공백 발생");
                    return;
                }

                //cursor = Database.getInstance().searchId(tIdSign);
                if(cursor.getCount() != 0){
                    Toast.makeText(SignUp.this, "존재하는 아이디입니다!", Toast.LENGTH_SHORT).show();
                    Log.d("xogud", "아이디 중복");

                }

                else if(!tPwSign.equals(tPwSign2)){
                    Toast.makeText(SignUp.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();


                }

                else if(!pwCheck){
                    Toast.makeText(SignUp.this, "비밀번호는 6~16자 영문 대 소문자, 숫자, 특수문자의 조합을 사용하세요!", Toast.LENGTH_SHORT).show();

                }

                else if(spaceCheck(tPwSign)){
                    Toast.makeText(SignUp.this, "비밀번호에 공백을 사용할 수 없습니다!", Toast.LENGTH_SHORT).show();

                }

                else if(inform_check.isChecked() == false){
                    Toast.makeText(SignUp.this, "이용약관 및 사용자 정보제공 \n동의는 필수입니다!", Toast.LENGTH_SHORT).show();

                }
                /*
                else{
                    Database.getInstance().insert(database, tIdSign, tPwSign, tCellNum);
                    Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
                    finish();
                    Log.d("xogud", "회원가입 완료");
                }

                cursor.close();

            }
        });
        */

        watchBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignUp.this, PopUp1.class);
                startActivityForResult(intent, 1);
            }
        });
        watchBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(SignUp.this, PopUp2.class);
                startActivityForResult(intent, 2);
            }
        });
    } //onCreate() 종료

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                result_1 = data.getStringExtra("result_1"); // 이용약관
                Log.d("xogud","ok1");
            }
        }

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                //데이터 받기
                result_2 = data.getStringExtra("result_2");
                Log.d("xogud","ok12");

            }
        }

        if(result_1 != null && result_2 != null ) {
            if (result_1.equals(closePopup_1) && result_2.equals(closePopup_2)) { // 정보 제공, 이용 약관 모두 확인 시
                inform_check.setChecked(true); // 체크 박스 체크
                inform_check.setEnabled(false); // 체크 박스 사용 불가 상태
            }

        }

    }
    /*
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Joinbtn: // 회원가입 버튼

                tIdSign = idSign.getText().toString();
                tPwSign = pwSign.getText().toString();
                tPwSign2 = pwSign2.getText().toString();
                tCellNum = cellPw.getText().toString();
                pwCheck = Pattern.matches("^(?=.*\\d)(?=.*[~`!@#$%^&*()-])(?=.*[a-zA-Z]).{6,16}$", tPwSign);

                if(tIdSign.trim().length() == 0 || tPwSign.trim().length() == 0 || tPwSign2.trim().length() == 0 || tCellNum.trim().length() == 0){
                    Toast.makeText(this, "빈칸 없이 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    Log.d("xogud", "공백 발생");
                    return;
                }

                //cursor = Database.getInstance().searchId(tIdSign);
                if(cursor.getCount() != 0){
                    Toast.makeText(this, "존재하는 아이디입니다!", Toast.LENGTH_SHORT).show();
                    Log.d("xogud", "아이디 중복");

                }

                else if(!tPwSign.equals(tPwSign2)){
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();


                }

                else if(!pwCheck){
                    Toast.makeText(this, "비밀번호는 6~16자 영문 대 소문자, 숫자, 특수문자의 조합을 사용하세요!", Toast.LENGTH_SHORT).show();

                }

                else if(spaceCheck(tPwSign)){
                    Toast.makeText(this, "비밀번호에 공백을 사용할 수 없습니다!", Toast.LENGTH_SHORT).show();

                }

                else if(inform_check.isChecked() == false){
                    Toast.makeText(this, "이용약관 및 사용자 정보제공 \n동의는 필수입니다!", Toast.LENGTH_SHORT).show();

                }

                else{
                    Database.getInstance().insert(database, tIdSign, tPwSign, tCellNum);
                    Toast.makeText(this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
                    finish();
                    Log.d("xogud", "회원가입 완료");
                }

                cursor.close(); // 꼭 닫아주어야 함
                break;

            case R.id.watch_btn1: // 이용약관 보기 버튼
                intent = new Intent(this, PopUp1.class);
                startActivityForResult(intent, 1); // requestCode 1

                break;

            case R.id.watch_btn2: // 정보제공 보기 버튼
                intent = new Intent(this, PopUp2.class);
                startActivityForResult(intent, 2); // requestCode 2

                break;

            case R.id.inform_check:
                if(result_1 == null || result_2 == null){ // 내용 미 확인 후 동의 체크 시
                    inform_check.setChecked(false);
                    Toast.makeText(this, "이용약관 및 개인정보정책 내용을 \n확인해주세요!", Toast.LENGTH_SHORT).show();

                }

                break;
        }

    }
       */
    public boolean spaceCheck(String spaceCheck) // 문자열 안에 스페이스 체크
    {
        for(int i = 0; i < spaceCheck.length(); i++)
        {

            if(spaceCheck.charAt(i) == ' ')
                return true;

        }
        return false;
    }

}