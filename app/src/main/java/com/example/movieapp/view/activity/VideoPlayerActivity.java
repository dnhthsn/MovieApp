package com.example.movieapp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.example.movieapp.util.Const;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class VideoPlayerActivity extends AppCompatActivity {
    private PlayerView videoPlayer;
    private ProgressBar progressBar;
    private ImageView fullScreen;

    private SimpleExoPlayer simpleExoPlayer;

    private Boolean flag = false;

    public static void starter(Context context, String url) {
        Intent intent = new Intent(context, VideoPlayerActivity.class);
        intent.putExtra(Const.Sender.url, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoPlayer = findViewById(R.id.exo_player);
        progressBar = findViewById(R.id.progress_bar);
        fullScreen = videoPlayer.findViewById(R.id.full_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Uri videoUri = Uri.parse(getIntent().getStringExtra(Const.Sender.url));

        LoadControl loadControl = new DefaultLoadControl();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(VideoPlayerActivity.this, trackSelector, loadControl);

        DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(videoUri, factory, extractorsFactory, null, null);
        videoPlayer.setPlayer(simpleExoPlayer);
        videoPlayer.setKeepScreenOn(true);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object o, int i) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {

            }

            @Override
            public void onLoadingChanged(boolean b) {

            }

            @Override
            public void onPlayerStateChanged(boolean b, int i) {
                if(i == Player.STATE_BUFFERING){
                    progressBar.setVisibility(View.VISIBLE);
                } else if (i == Player.STATE_READY){
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onRepeatModeChanged(int i) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean b) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException e) {

            }

            @Override
            public void onPositionDiscontinuity(int i) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag){
                    fullScreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_fullscreen_24));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    flag = false;
                } else {
                    fullScreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_fullscreen_exit_24));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    flag = true;
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false);
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.getPlaybackState();
    }
}