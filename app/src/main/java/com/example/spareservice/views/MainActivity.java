package com.example.spareservice.views;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.spareservice.R;

public class MainActivity extends AppCompatActivity {

    ImageView launchViewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        launchViewImage = findViewById(R.id.launchScreen_image);

        Glide.with(MainActivity.this).load(R.drawable.spareservicelogomini).into(launchViewImage);

        Runnable runnable = () -> navigateToHome();
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, 2000);
    }

    private void navigateToHome() {
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
        finish();
    }
}
