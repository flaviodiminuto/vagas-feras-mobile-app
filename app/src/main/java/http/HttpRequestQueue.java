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

import java.io.UnsupportedEncodingException;

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
                Throwable::printStackTrace) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String body = "";
                try {
                     body = request.requestBody();
                    return body == null ? null : body.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, "utf-8");
                    return null;
                }
            }

        };
        queue.add(stringRequest);
        return true;
    }
}
