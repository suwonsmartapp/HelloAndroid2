
package com.example.suwonsmartapp.androidexam.filemanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.filemanager.event.ChangePathEvent;
import com.example.suwonsmartapp.androidexam.filemanager.views.ManagerTreeView;

import java.io.File;
import java.util.HashMap;

import de.greenrobot.event.EventBus;

public class FileManagerActivity extends AppCompatActivity {

    private static final String TAG = FileListFragment.class.getSimpleName();
    // ActionBar에 표시할 경로 관리 Map (getAbsolutePath, getName)
    private HashMap<String, String> mPathMap;
    private Toolbar mToolbar;
    private LinearLayout mToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        // Toolbar 설정
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbarLayout = (LinearLayout) mToolbar.getRootView().findViewById(R.id.tree_container);

        mPathMap = new HashMap<>();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        changePath(path);
    }

    private void removeManagerTreeView(View removeView) {
        mToolbarLayout.removeView(removeView);
    }

    private void addManagerTreeView(String tag) {
        ManagerTreeView managerTreeView = new ManagerTreeView(this);
        managerTreeView.setName(tag);
        managerTreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack((String) v.getTag(),
                        0);
            }
        });

        managerTreeView.setTitle(mPathMap.get(tag));
        mToolbarLayout.addView(managerTreeView);
    }

    private void changePath(String path) {
        File file = new File(path);
        mPathMap.put(path, file.getName());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FileListFragment.newInstance(path))
                .addToBackStack(path) // stack 에 Fragment를 하나씩 쌓을 때
                .commit();

        addManagerTreeView(file.getAbsolutePath());
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
            ManagerTreeView removeView = (ManagerTreeView) mToolbarLayout.getChildAt(mToolbarLayout
                    .getChildCount() - 1);
            removeManagerTreeView(removeView);
        }
    }
}
