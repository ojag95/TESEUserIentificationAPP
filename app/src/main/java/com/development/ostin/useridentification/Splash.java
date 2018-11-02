package com.development.ostin.useridentification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cargar();



    }
    public void cargar()
    {
        new Thread (new Runnable() {
            public void run() {

                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(Splash.this,LogIn.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }}).start();

    }
}
