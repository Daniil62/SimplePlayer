package ru.job4j.simple_player;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton play = findViewById(R.id.play_button_imageView);
        ImageButton pause = findViewById(R.id.pause_button_imageView);
        player = MediaPlayer.create(this, R.raw.s_teh_por_kak_sgoreli_doma);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    player.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    player.pause();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        player.release();
        player = null;
    }
}
