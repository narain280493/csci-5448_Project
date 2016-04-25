package com.mycuoppnew.mycuoppnew2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {

    private  String email,firstname,lastname;
    private TextView firstnametv,emailtv;
    private RequestQueue requestQueue;
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        email = bundle.getString("email");
        firstnametv = (TextView) findViewById(R.id.firstname);
        emailtv = (TextView) findViewById(R.id.email);

        String URL = "http://10.0.2.2:8888/mycuoppnew2/profile.php?email="+email;
        requestQueue = Volley.newRequestQueue(this);



                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray profile = jsonObject.getJSONArray("profile");
                            JSONObject profiledata = profile.getJSONObject(0);
                             firstname = profiledata.getString("Firstname");
                             lastname = profiledata.getString("Lastname");



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String output = firstname+"\t"+lastname;
                        firstnametv.setText(output);
                        emailtv.setText(email);


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(request);
            }





    }


