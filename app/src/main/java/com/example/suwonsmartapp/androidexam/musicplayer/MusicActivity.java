package com.example.suwonsmartapp.androidexam.musicplayer;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;

/**
 * Created by junsuk on 15. 10. 1..
 */
public class MusicActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_PICK_MUSIC = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music);

        findViewById(R.id.btn_file_pick).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_file_pick:
                pickFile();
                break;
        }
    }

    private void pickFile() {
        // 음악 파일을 선택 하고
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(Intent.createChooser(intent, "음악 파일 선택"), REQUEST_PICK_MUSIC);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 정보들을 화면에 표시
        if (resultCode == RESULT_OK && requestCode == REQUEST_PICK_MUSIC) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(getApplicationContext(), data.getData());
            String album = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String title = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            String artist = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String duration = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

            Toast.makeText(MusicActivity.this, album + ", " + title + ", " + artist + ", " + duration, Toast.LENGTH_SHORT).show();
        }

    }
}
