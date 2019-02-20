package com.atmecs.atmecsmuse.atmecsmuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button Button_muse_monitor =(Button) findViewById(R.id.button_to_muse);
        Button_muse_monitor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent launchMuseMonitorApp = getPackageManager().getLaunchIntentForPackage(getString(R.string.muse_monitor_package));
                startActivity(launchMuseMonitorApp);
            }
        });

    }

}
