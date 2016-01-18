package com.example.brayanasdrubal.appalice;

import android.os.Bundle;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class video extends YouTubeBaseActivity  {
    public YouTubePlayerView youtube;
    public YouTubePlayer.OnInitializedListener onInitializedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        youtube = (YouTubePlayerView) findViewById(R.id.youtube); //
        onInitializedListener =new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("-UCHsqxBqwY");
            }

            @Override
            public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        youtube.initialize("AIzaSyDJdcNkjCssAwdiE1NiR8Z4XlVT28hFexE", onInitializedListener);
    }


    public void callYoutube(View view){
    }
}

