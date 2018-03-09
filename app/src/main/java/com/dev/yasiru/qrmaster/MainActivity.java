package com.dev.yasiru.qrmaster;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 1000;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        progress = new ProgressDialog(this, R.style.MyTheme);
        progress.setProgressStyle(android.R.style.Widget_ProgressBar_Large);
        progress.setCancelable(false);

        progress.show();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent logIntent = new Intent(MainActivity.this,Dashboard.class);
                MainActivity.this.startActivity(logIntent);
                MainActivity.this.finish();
                progress.dismiss();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
