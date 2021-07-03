package http;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import contratos.Inscrito;

public class HttpRequestQueue {

    public static boolean send(RequestGet request){

        RequestQueue queue = Volley.newRequestQueue(request.getContext());
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                request.getUri(), request::finish,
                Throwable::printStackTrace);
        queue.add(stringRequest);
        return true;
    }

    public static boolean send(RequestPost request){
        RequestQueue queue = Volley.newRequestQueue(request.getContext());
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,
                request.getUri(), request::finish,
                error -> {
                    try {
                        String response = new String(error.networkResponse.data,StandardCharsets.UTF_8);
                        String body= new JSONObject(response)
                                .getJSONObject("data")
                                .get("causa")
                                .toString();
                        request.finish(body);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String body = "";
                body = request.requestBody();
                return body == null ? null : body.getBytes(StandardCharsets.UTF_8);
            }


        };
        queue.add(stringRequest);
        return true;
    }
}
