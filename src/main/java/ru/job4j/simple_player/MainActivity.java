package ru.job4j.simple_player;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;
    private int position;
    private Field[] tracks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton previous = findViewById(R.id.previous_imageButton);
        ImageButton play = findViewById(R.id.play_imageButton);
        ImageButton pause = findViewById(R.id.pause_imageButton);
        ImageButton next = findViewById(R.id.next_imageButton);
        position = 0;
        tracks = R.raw.class.getFields();
        player = MediaPlayer.create(this, trackSelector(position));
        previous.setOnClickListener(v -> previousClick());
        play.setOnClickListener(v -> {
            try {
                player.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        pause.setOnClickListener(v -> {
            try {
                player.pause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        next.setOnClickListener(v -> nextClick());
    }
    private void previousClick() {
        player.pause();
        if (position != 0) {
            --position;
        } else {
            position = tracks.length - 1;
        }
        player = MediaPlayer.create(this, trackSelector(position));
        player.start();
    }
    private void nextClick() {
        player.pause();
        if (position != tracks.length - 1) {
            ++position;
        } else {
            position = 0;
        }
        player = MediaPlayer.create(this, trackSelector(position));
        player.start();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
        outState.putBoolean("isPlaying", player.isPlaying());
        outState.putInt("currentPosition", player.getCurrentPosition());
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position = savedInstanceState.getInt("position");
        player = MediaPlayer.create(this, trackSelector(position));
        player.seekTo(savedInstanceState.getInt("currentPosition"));
        if (savedInstanceState.getBoolean("isPlaying")) {
            player.start();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        player.release();
        player = null;
    }
    private int trackSelector(int position) {
        int trackId = 0;
        if (position < tracks.length && position >= 0) {
            try {
            trackId = tracks[position].getInt(tracks[position]);
            } catch (IllegalAccessException e) {
            e.printStackTrace();
            }
        }
        return trackId;
    }
}
