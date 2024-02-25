package com.example.intro;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private TextView textView;
    private ImageView imageView;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.logo);
        textView = findViewById(R.id.texto);
        button = findViewById(R.id.boton);
        videoView = findViewById(R.id.video);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.videoinicio;
        Uri uri = Uri.parse(videoPath);

        videoView.setVideoURI(uri);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.setVisibility(View.GONE);
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                textView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.parpadeo));
            }
        });

        videoView.start();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFragmento();
                textView.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
            }
        });
    }
    private void abrirFragmento() {
        Inicio inicio = new Inicio();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(android.R.id.content, inicio);

        transaction.addToBackStack(null);

        transaction.commit();
    }
}