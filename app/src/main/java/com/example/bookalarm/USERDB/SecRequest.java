package com.example.bookalarm.USERDB;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SecRequest extends StringRequest {
    final static private String url = "http://xogud019.dothome.co.kr/UserDelete.php";
    private Map<String, String> map;

    public SecRequest(String userCell,Response.Listener<String> listener){
        super(Method.POST, url, listener, null);

        map = new HashMap<>();
        map.put("userCell",userCell);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
