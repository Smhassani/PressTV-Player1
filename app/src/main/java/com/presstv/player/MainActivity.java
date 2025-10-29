package com.presstv.player;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;

public class MainActivity extends AppCompatActivity {
    private ExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PlayerView playerView = findViewById(R.id.player_view);
        player = new ExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        String url = "https://live.presstv.ir/hls/presstv_5_482/index.m3u8";
        MediaItem mediaItem = MediaItem.fromUri(url);

        HlsMediaSource hlsMediaSource = new HlsMediaSource.Factory(
                () -> new DefaultHttpDataSource.Factory().createDataSource()
        ).createMediaSource(mediaItem);

        player.setMediaSource(hlsMediaSource);
        player.setPlayWhenReady(true);
        player.prepare();
    }

    @Override
    protected void onDestroy() {
        if (player != null) {
            player.release();
        }
        super.onDestroy();
    }
}