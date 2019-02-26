package com.atmecs.atmecsmuse.atmecsmuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int empId = getIntent().getIntExtra("EmpId", 0);
        setContentView(R.layout.activity_home);
        Button Button_muse_monitor =(Button) findViewById(R.id.button_to_muse);
        Button_muse_monitor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("EMP ID:"+empId);
                requestSessionStart(empId);
            }
        });

    }
    public void requestSessionStart(int eid){

        try {
            String url = getString(R.string.sessionstart_url);
            JSONObject reqBody = new JSONObject();
            reqBody.put("EmpId",eid);
            System.out.println(reqBody.toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, reqBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            Intent launchMuseMonitorApp = getPackageManager().getLaunchIntentForPackage(getString(R.string.muse_monitor_package));
                            startActivity(launchMuseMonitorApp);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            System.out.println(error.toString());
                            Toast.makeText(getApplicationContext(),"HTTP Error - unable to start Muse Session", Toast.LENGTH_LONG).show();

                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
            // Simulate network access.
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
