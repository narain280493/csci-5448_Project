package com.mycuoppnew.mycuoppnew2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostopportunitiesActivity extends AppCompatActivity {

    private String jobname, jobpay, jobdesc;
    private EditText jobnameet,jobpayet,jobdescet;
    private Button postjob;
    private RequestQueue requestQueue;
    private StringRequest request;
    private String email;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postopportunities);
        jobnameet = (EditText) findViewById(R.id.jobname1);
        //jobname = jobnameet.getText().toString();

        jobpayet = (EditText) findViewById(R.id.jobpay1);
        //jobpay = jobpayet.getText().toString();
        jobdescet = (EditText) findViewById(R.id.jobdesc1);
        //jobdesc = jobdescet.getText().toString();



        postjob = (Button) findViewById(R.id.buttonpost1);
        Intent emailintent = getIntent();
        Bundle bundle = emailintent.getExtras();
        email = bundle.getString("email");

        final String URL = "http://10.0.2.2:8888/mycuoppnew2/Postjob.php?";

        requestQueue = Volley.newRequestQueue(this);

        postjob.setOnClickListener(new View.OnClickListener() {

                        @Override
            public void onClick(View view) {



                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                           JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "" + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                                i.putExtra("email", email);
                                startActivity(i);

                            } else {
                                Toast.makeText(getApplicationContext(), "Error: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> hashMap = new HashMap<String, String>();
                        hashMap.put("jobname", jobnameet.getText().toString());
                        hashMap.put("jobdesc", jobdescet.getText().toString());
                        hashMap.put("email", email);
                        hashMap.put("jobpay", jobpayet.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });
    }
}
