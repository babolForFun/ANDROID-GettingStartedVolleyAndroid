package com.babolforfun.volley.requestvolley.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.babolforfun.volley.requestvolley.R;
import com.babolforfun.volley.requestvolley.constants.Constants;
import com.babolforfun.volley.requestvolley.controllers.ApplicationController;
import com.babolforfun.volley.requestvolley.controllers.VolleySingleton;

import java.util.Hashtable;
import java.util.Map;

public class Homepage extends AppCompatActivity {

    // View
    private Button   btRequest;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        findViewById();

        setListener();


    }

    /**
     * Find view by id
     */
    private void findViewById() {
        btRequest  = (Button)  findViewById(R.id.bt_request);
        tvResponse = (TextView)findViewById(R.id.tv_response);
    }

    /**
     * Set listener
     */
    private void setListener(){
        btRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    /**
     * Send request
     */
    private void sendRequest() {

        //Progress dialog - I don't like it but seems necessary for most of the users
        final ProgressDialog loading = ProgressDialog.show(
                this,
                "Sending request",
                "Please wait..",
                false,
                false
        );

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_UPLOAD,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        showResults(s);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                        ApplicationController.showToast(volleyError.getMessage());
                        Log.d(Constants.TAG_NAME, "HOMEPAGE - response error" + volleyError.getMessage());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //Post params - Not necessary for this example
                Map<String,String> params = new Hashtable<>();
                params.put(Constants.KEY_USERNAME, "username");
                params.put(Constants.KEY_PASSWORD, "password");

                return params;
            }
        };

        // Timeout handler
        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }

    /**
     * Show result in the text view
     */
    private void showResults(String response) {
        tvResponse.setText(response);
    }


}
