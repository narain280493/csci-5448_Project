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


public class SearchdetailActivity extends ListActivity {
    private String keyword, minpay, maxpay;
    private String checkboxoncampusjobs, checkboxstudentopportunities;
    private RequestQueue requestQueue;
    private StringRequest request;
    private String jobname,jobid,job_posted_date,job_type,job_pay,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdetail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        keyword = bundle.getString("keyword");
        minpay = bundle.getString("minpay");
        maxpay = bundle.getString("maxpay");
        email = bundle.getString("email");
        checkboxoncampusjobs = bundle.getString("checkboxoncampusjobs");
        checkboxstudentopportunities = bundle.getString("checkboxstudentopportunities");
       final  ListView listView = getListView();
        String URL = "http://10.0.2.2:8888/mycuoppnew2/search.php?keyword="+keyword+"&minpay="+minpay+"&maxpay="+maxpay+"&checkboxoncampusjobs="+checkboxoncampusjobs+"&checkboxstudentopportunities="+checkboxstudentopportunities;
        final List<Map<String, String>> joblist = new ArrayList<Map<String, String>>();
        requestQueue = Volley.newRequestQueue(this);


        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray searchresult = jsonObject.getJSONArray("job_search");
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
                hashMap.put("keyword", keyword.toString());

                return hashMap;
            }
        };

        requestQueue.add(request);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String jobdetail1 = ((TextView) view).getText().toString();
                String newsplit =jobdetail1.split(" ")[0].split(":")[1];
                Intent i = new Intent(getApplicationContext(), jobdetail.class);
                i.putExtra("jobid", newsplit);
                i.putExtra("emailuser", email);
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





