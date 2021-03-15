package com.example.bookalarm.USERDB;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String url = "http://xogud019.dothome.co.kr/Login.php";
    private Map<String, String> map;

    public LoginRequest(String userID, String userPWD, Response.Listener<String> listener){
        super(Method.POST, url, listener, null);

        map = new HashMap<>();
        map.put("userID",userID);
        map.put("userPWD",userPWD);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
