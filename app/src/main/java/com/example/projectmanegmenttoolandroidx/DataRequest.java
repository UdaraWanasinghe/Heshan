package com.example.projectmanegmenttoolandroidx;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

public class DataRequest {
    private final Context context;
    private final String url;
    private final int method;
    private final Map<String, String> headers;
    private final JSONObject body;
    public int statusCode;

    public DataRequest(Context context, String url, int method, Map<String, String> headers, JSONObject body) {
        this.context = context;
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }

    public void sendRequest(Response.Listener<JSONObject> responseListener, Response.ErrorListener errorListener) {
        JsonObjectRequest request = new JsonObjectRequest(
                method,
                url,
                body,
                responseListener,
                errorListener
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers != null) {
                    return headers;
                }
                return super.getHeaders();
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                if (response != null) {
                    statusCode = response.statusCode;
                }
                return super.parseNetworkResponse(response);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                if (volleyError.networkResponse != null) {
                    statusCode = volleyError.networkResponse.statusCode;
                }
                return super.parseNetworkError(volleyError);
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

}
