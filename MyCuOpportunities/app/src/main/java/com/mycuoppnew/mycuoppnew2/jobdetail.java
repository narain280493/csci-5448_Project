package com.mycuoppnew.mycuoppnew2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View;

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

public class jobdetail extends AppCompatActivity {
    private String job_id;
    private String id;
    private RequestQueue requestQueue;
    private TextView jobnamev,jobpayv,posteddatev,jobdescv,jobtypev,postedbyv;
    private StringRequest request;
    private String jobname = "";
    private String jobdesc = "";
    private String jobid = "";
    private String job_posted_date="";
    private String job_type="";
    private String job_pay="";
    private  String firstname="";
    private  String email="";
    private Button apply;
    private Intent emailIntent;
    private EditText usermessage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobdetail);
        jobnamev = (TextView) findViewById(R.id.jobname1);
        jobdescv = (TextView) findViewById(R.id.jobdesc);
        jobpayv = (TextView) findViewById(R.id.jobpay);
        posteddatev = (TextView) findViewById(R.id.dateposted);
        jobtypev = (TextView) findViewById(R.id.jobtype);
        postedbyv = (TextView) findViewById(R.id.postedby);
        apply = (Button) findViewById(R.id.buttonapply);
        usermessage = (EditText) findViewById(R.id.usermessage);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        job_id = bundle.getString("jobid");
        String URL = "http://10.0.2.2:8888/mycuoppnew2/jobdetail.php?job_id="+job_id;
        final List<Map<String, String>> joblist = new ArrayList<Map<String, String>>();
        requestQueue = Volley.newRequestQueue(this);



        apply.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                emailIntent.setType("plain/text");
                String subject = "Application for "+jobname;
                String message = usermessage.getText().toString();

                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);

                startActivity(Intent.createChooser(emailIntent, "Choose a Email Client."));

            }
        });


        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray searchresult = jsonObject.getJSONArray("job_detail");
                    for (int i = 0; i < searchresult.length(); i++) {


                        JSONObject jobdata = searchresult.getJSONObject(i);
                        jobid = jobdata.getString("job_id");
                        jobname = jobdata.getString("job_name");
                        jobdesc = jobdata.getString("job_description");
                        job_posted_date = jobdata.getString("job_posted_date");
                        job_type = jobdata.getString("job_type");
                        job_pay = jobdata.getString("pay");
                        firstname = jobdata.getString("Firstname");
                        email = jobdata.getString("email");

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jobnamev.setText(jobname);
                jobdescv.setText(jobdesc);
                jobpayv.setText("$"+job_pay+"/hour");
                posteddatev.setText(job_posted_date);
                jobtypev.setText(job_type);
                postedbyv.setText("Posted By "+firstname+".");





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();

                return hashMap;
            }
        };

        requestQueue.add(request);

    }

}
