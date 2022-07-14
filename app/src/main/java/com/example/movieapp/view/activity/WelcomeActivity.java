package com.example.movieapp.view.activity;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.intro;

        binding.videoIntro.setVideoURI(Uri.parse(videoPath));
        binding.videoIntro.start();

        new Thread(){
            public void run(){
                try {
                    sleep(5000);
                    LoginActivity.starter(WelcomeActivity.this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}