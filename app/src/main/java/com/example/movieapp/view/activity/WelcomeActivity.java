package com.example.movieapp.view.activity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;

public class WelcomeActivity extends AppCompatActivity {
    private static final int MESSAGE_COUNT_DOWN = 100;

    private VideoView videoIntro;

    private Handler handler;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.intro;
        videoIntro = findViewById(R.id.video_intro);
        videoIntro.setVideoURI(Uri.parse(videoPath));
        videoIntro.start();

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_COUNT_DOWN:
                        break;
                    case 234:
                        LoginActivity.starter(WelcomeActivity.this);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        MediaPlayer mediaPlayer = MediaPlayer.create(WelcomeActivity.this, R.raw.intro_music);
        mediaPlayer.start();
        super.onStart();
        new CountDown().start();
    }

    class CountDown extends Thread {
        @Override
        public void run() {
            int count = 5;
            while (count > 0) {
                count--;
                Message message = new Message();
                message.what = MESSAGE_COUNT_DOWN;
                message.arg1 = count;
                handler.sendMessage(message);
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    Log.d("loi:", e.getMessage());
                }
            }

            handler.sendEmptyMessage(234);
        }
    }
}