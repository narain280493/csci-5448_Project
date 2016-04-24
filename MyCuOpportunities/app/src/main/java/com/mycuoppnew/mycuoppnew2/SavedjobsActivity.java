package com.mycuoppnew.mycuoppnew2;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class SavedjobsActivity extends ListActivity {


    private String email;
    private TextView emailv;
    private RequestQueue requestQueue;
    private StringRequest request;
    private String jobname = "";
    private String jobdesc = "";
    private String jobid = "";
    private String job_posted_date="";
    private String job_type="";
    private String job_pay="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedjobs);


        Intent emailintent = getIntent();
        Bundle bundle = emailintent.getExtras();
        email = bundle.getString("email");

        // emailv = (TextView) findViewById(R.id.email);
        //emailv.setText(email);


        final ListView listView = getListView();
        String URL = "http://10.0.2.2:8888/mycuoppnew2/savedjobs.php?email="+email;
        final List<Map<String, String>> joblist = new ArrayList<Map<String, String>>();
        requestQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray searchresult = jsonObject.getJSONArray("savedjobs");
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
                // String name = (String) obj.get("name");
                // Log.d("Yourtag", name);
                String jobdetail1 = ((TextView) view).getText().toString();
                String newsplit = jobdetail1.split(" ")[0].split(":")[1];
                Intent i = new Intent(getApplicationContext(), jobdetail.class);
                i.putExtra("jobid", newsplit);
                startActivity(i);


            }
        });


    }


    private HashMap<String, String> createjob(String jobname, String jobid) {
        HashMap<String, String> jobidname = new HashMap<String, String>();
        jobidname.put(jobname, jobid);
        return jobidname;

    }


}
