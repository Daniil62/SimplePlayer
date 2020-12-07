package ru.job4j.simple_player;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.TextView;

public class TestActivity extends AppCompatActivity {
    private MediaPlayer player;
    private Uri uri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        ImageButton play = findViewById(R.id.play_imageButton);
        ImageButton pause = findViewById(R.id.pause_imageButton);
        TextView name = findViewById(R.id.track_name_textView);
        Intent intent = getIntent();
        if (intent != null) {
            this.uri = Uri.parse(intent.getStringExtra("uri"));
            name.setText(intent.getStringExtra("track_name"));
        }
        player = MediaPlayer.create(this, uri);
        try {
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        play.setOnClickListener(v -> player.start());
        pause.setOnClickListener(v -> player.pause());
    }
    @Override
    protected void onStop() {
        super.onStop();
        player.release();
        player = null;
    }
}
