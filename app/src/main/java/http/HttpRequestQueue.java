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

public class HttpRequestQueue {

    public static boolean send(RequestGet requestGet){

        RequestQueue queue = Volley.newRequestQueue(requestGet.getContext());
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET,
                requestGet.getUri(), requestGet::finish,
                Throwable::printStackTrace);
        queue.add(stringRequest);
        return true;
    }

    public static boolean send(RequestPost requestPost){
        RequestQueue queue = Volley.newRequestQueue(requestPost.getContext());
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.POST,
                requestPost.getUri(), requestPost::finish,
                Throwable::printStackTrace) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String body = "";
                try {
                     body = requestPost.requestBody();
                    return body == null ? null : body.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", body, "utf-8");
                    return null;
                }
            }
//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                String responseString = "";
//                if (response != null) {
//                    responseString = String.valueOf(response.statusCode);
//                    // can get more details such as response.headers
//                }
//                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//            }

        };
        queue.add(stringRequest);
        return true;
    }

}
