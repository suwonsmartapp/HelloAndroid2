
package com.example.suwonsmartapp.androidexam.filemanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.filemanager.event.AddTreeViewEvent;
import com.example.suwonsmartapp.androidexam.filemanager.event.ChangePathEvent;
import com.example.suwonsmartapp.androidexam.filemanager.event.EventBusEvent;
import com.example.suwonsmartapp.androidexam.filemanager.views.CustomToolbar;

import java.io.File;

import de.greenrobot.event.EventBus;

public class FileManagerActivity extends AppCompatActivity {

    private static final String TAG = FileListFragment.class.getSimpleName();
    // ActionBar에 표시할 경로 관리 Map (getAbsolutePath, getName)
    private CustomToolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        // Toolbar 설정
        mToolbar = (CustomToolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        changePath(path);
    }

    private void changePath(String path) {
        File file = new File(path);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FileListFragment.newInstance(path))
                .addToBackStack(path) // stack 에 Fragment를 하나씩 쌓을 때
                .commit();

        mToolbar.addManagerTreeView(file.getAbsolutePath());
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

    public void onEvent(EventBusEvent event) {
        if (event instanceof ChangePathEvent) {
            File file = ((ChangePathEvent) event).file;
            changePath(file.getAbsolutePath());

        } else if (event instanceof AddTreeViewEvent) {
            getSupportFragmentManager().popBackStack(((AddTreeViewEvent) event).tag,
                    0);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // 스택이 비면 앱 종료 처리
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }

        Log.e(TAG, getSupportFragmentManager().getBackStackEntryCount() + "");

        // Back 키 눌렀을 때, Toolbar 에 경로를 하나씩 삭제
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            mToolbar.removeLastManagerTreeView();
        }
    }
}
