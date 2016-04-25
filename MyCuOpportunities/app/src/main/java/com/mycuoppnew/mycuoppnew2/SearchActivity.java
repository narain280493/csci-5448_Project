package com.mycuoppnew.mycuoppnew2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {


    private EditText keyword, minpay, maxpay;
    private CheckBox checkboxoncampusjobs,checkboxstudentopportunities;
    private Button search,appliedjobs,savedjobs,postjobs,postedjobs,profile;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent emailintent = getIntent();
        Bundle bundle = emailintent.getExtras();
        email = bundle.getString("email");


        keyword = (EditText) findViewById(R.id.keyword);
        minpay = (EditText) findViewById(R.id.minpay);
        maxpay = (EditText) findViewById(R.id.maxpay);
        checkboxoncampusjobs =(CheckBox) findViewById(R.id.checkBoxoncampusjobs);
        checkboxstudentopportunities = (CheckBox) findViewById(R.id.checkBoxstudentcreatedopportunities);

        appliedjobs =(Button) findViewById(R.id.buttonappliedjobs);
        savedjobs =(Button) findViewById(R.id.buttonsavedjobs);
        postjobs = (Button) findViewById(R.id.buttonpostjobs);
        postedjobs = (Button) findViewById(R.id.buttonpostedjobs);
        profile = (Button) findViewById(R.id.buttonprofile);

        search = (Button) findViewById(R.id.buttonsearch);



       search.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SearchActivity.this, SearchdetailActivity.class);
                myIntent.putExtra("keyword", keyword.getText().toString());
                myIntent.putExtra("minpay", minpay.getText().toString());
                myIntent.putExtra("maxpay", maxpay.getText().toString());
                myIntent.putExtra("email", email);
               if(checkboxoncampusjobs.isChecked())
               {
                   myIntent.putExtra("checkboxoncampusjobs", "true");
               }
                else{
                   myIntent.putExtra("checkboxoncampusjobs", "false");
               }

                if(checkboxstudentopportunities.isChecked())
                {
                    myIntent.putExtra("checkboxstudentopportunities", "true");
                }
                else{
                    myIntent.putExtra("checkboxstudentopportunities", "false");
                }

                startActivity(myIntent);

            }
        });



        appliedjobs.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {



                Intent appliedintent = new Intent(SearchActivity.this, AppliedjobActivity.class);
                appliedintent.putExtra("email", email);


                startActivity(appliedintent);

            }
        });


        profile.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {



                Intent profileintent = new Intent(SearchActivity.this, ProfileActivity.class);
                profileintent.putExtra("email", email);


                startActivity(profileintent);

            }
        });



        savedjobs.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {



                Intent savedintent = new Intent(SearchActivity.this, SavedjobsActivity.class);
                savedintent.putExtra("email", email);
                startActivity(savedintent);

            }
        });


        postjobs.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent postintent = new Intent(SearchActivity.this, PostopportunitiesActivity.class);
                postintent.putExtra("email", email);
                startActivity(postintent);

            }
        });


        postedjobs.setOnClickListener( new OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent postintent = new Intent(SearchActivity.this, PostedjobsActivity.class);
                postintent.putExtra("email", email);
                startActivity(postintent);

            }
        });

    }



}
