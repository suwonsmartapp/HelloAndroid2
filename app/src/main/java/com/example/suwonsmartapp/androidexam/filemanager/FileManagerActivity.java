
package com.example.suwonsmartapp.androidexam.filemanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.filemanager.event.ChangePathEvent;

import java.io.File;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class FileManagerActivity extends AppCompatActivity {

    // ActionBar에 표시할 경로 관리 Map (getAbsolutePath, getName)
    private HashMap<String, String> mPathMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        mPathMap = new HashMap<>();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(FileManagerActivity.this, path, Toast.LENGTH_SHORT).show();

        changePath(path);
    }

    private void changePath(String path) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FileListFragment.newInstance(path))
                .addToBackStack(path) // stack 에 Fragment를 하나씩 쌓을 때
                .commit();

        File file = new File(path);
        mPathMap.put(path, file.getName());
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    public void onEvent(ChangePathEvent event) {
        File file = event.file;

        changePath(file.getAbsolutePath());
    }
}
