package com.mycuoppnew.mycuoppnew2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppliedjobActivity extends ListActivity {

    private RequestQueue requestQueue;
    private StringRequest request;
    private String email,jobname,jobid,job_posted_date,job_type,job_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliedjob);


        Intent emailintent = getIntent();
        Bundle bundle = emailintent.getExtras();
        email = bundle.getString("email");
        final ListView listView = getListView();
        String URL = "http://10.0.2.2:8888/mycuoppnew2/appliedjobs.php?email="+email;
        final List<Map<String, String>> joblist = new ArrayList<Map<String, String>>();
        requestQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray searchresult = jsonObject.getJSONArray("appliedjobs");
                    for (int i = 0; i < searchresult.length(); i++) {


                        JSONObject jobdata = searchresult.getJSONObject(i);
                        jobid = jobdata.getString("job_id");
                        jobname = jobdata.getString("job_name");
                        job_posted_date = jobdata.getString("job_posted_date");
                        job_type = jobdata.getString("job_type");
                        job_pay = jobdata.getString("pay");
                        String outPut = "Jobid:"+jobid+" "+jobname + "\nPay: $"+job_pay+"/hour\n " +job_type+"\nPosted: "+job_posted_date;

                        joblist.add(createjob("jobs", outPut));


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),joblist,android.R.layout.simple_list_item_1,
                        new String[] {"jobs"}, new int[] {android.R.id.text1});

                listView.setAdapter(simpleAdapter);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("email", email);

                return hashMap;
            }
        };

        requestQueue.add(request);


    }


    private HashMap<String, String> createjob(String jobname, String jobid) {
        HashMap<String, String> jobidname = new HashMap<String, String>();
        jobidname.put(jobname, jobid);
        return jobidname;

    }


}
