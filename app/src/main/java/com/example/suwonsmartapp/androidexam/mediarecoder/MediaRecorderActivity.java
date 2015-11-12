
package com.example.suwonsmartapp.androidexam.mediarecoder;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;

import java.io.IOException;

/**
 * Created by junsuk on 2015. 11. 12..
 */
public class MediaRecorderActivity extends Activity implements View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    private MediaRecorder mRecorder;

    private boolean mStartRecording;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mediarecoder);

        findViewById(R.id.btn_media).setOnClickListener(this);

        // 6.0 권한 체크
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                // 사용자가 이전에 거부를 했을 경우
                ActivityCompat.requestPermissions(this,
                        new String[] {
                            Manifest.permission.RECORD_AUDIO
                        },
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            } else {
                // 권한이 없을 때 권한 요청
                ActivityCompat.requestPermissions(this,
                        new String[] {
                            Manifest.permission.RECORD_AUDIO
                        },
                        MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // 사용자가 이전에 거부를 했을 경우
                ActivityCompat.requestPermissions(this,
                        new String[] {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                // 권한이 없을 때 권한 요청
                ActivityCompat.requestPermissions(this,
                        new String[] {
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        },
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        v.setSelected(!v.isSelected());
        mStartRecording = !mStartRecording;

        if (mStartRecording) {
            playRecording();
        } else {
            stopRecording();
        }
    }

    private void playRecording() {
        // 순서가 매우 중요함
        // http://developer.android.com/intl/ko/guide/topics/media/audio-capture.html
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/audiorecordtest.3gp";
        mRecorder.setOutputFile(path);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Toast.makeText(MediaRecorderActivity.this, "prepare failed", Toast.LENGTH_SHORT).show();
        }

        mRecorder.start();
    }

    private void stopRecording() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }
}
