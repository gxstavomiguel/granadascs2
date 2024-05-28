package com.example.granadascs2;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout videoContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner mapSpinner = findViewById(R.id.mapSpinner);
        videoContainer = findViewById(R.id.videoContainer);

        mapSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMap = parent.getItemAtPosition(position).toString();
                loadMapVideos(selectedMap);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void updateConnector(String drive){

    }

    private void loadMapVideos(String map) {
        videoContainer.removeAllViews();

        switch (map) {
            case "Inferno":
                // addVideo(R.raw.inferno_grenade_2, "Pixel 2 - Inferno");
                break;
            case "Dust 2":
                addVideo(R.raw.dust_caixagorda, "Caixa Gorda - Dust 2");
                addVideo(R.raw.dust_smokect, "Smoke Base CT - Dust 2");
                break;
            case "Mirage":
                addVideo(R.raw.mirage_caverna, "Caverna - Mirage");
                addVideo(R.raw.mirage_janela, "Janela - Mirage");
                break;
        }
    }

    private void addVideo(int videoResId, String description) {
        VideoView videoView = new VideoView(this);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + videoResId);
        videoView.setVideoURI(videoUri);

        Button playPauseButton = new Button(this);
        playPauseButton.setText("Play");
        playPauseButton.setBackgroundColor(getResources().getColor(R.color.purple));
        playPauseButton.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                playPauseButton.setText("Play");
            } else {
                videoView.start();
                playPauseButton.setText("Pause");
            }
        });

        TextView textView = new TextView(this);
        textView.setText(description);
        textView.setTextColor(getResources().getColor(android.R.color.black));

        videoContainer.addView(textView);
        videoContainer.addView(videoView);
        videoContainer.addView(playPauseButton);

        // Start video preparation after adding play/pause button
        videoView.setOnPreparedListener(mp -> mp.setLooping(true));  // Loop the video
    }
}
