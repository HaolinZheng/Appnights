package com.example.intro;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView textView;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.video);
        imageView = findViewById(R.id.logo);
        textView = findViewById(R.id.texto);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.videoinicio;
        Uri uri = Uri.parse(videoPath);

        videoView.setVideoURI(uri);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                textView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.parpadeo));
            }
        });

        videoView.start();
    }
}