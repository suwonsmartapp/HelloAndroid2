
package com.example.suwonsmartapp.androidexam.filemanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

        mPathMap = new HashMap<>();

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FileListFragment.newInstance(path))
                .commit();

        File file = new File(path);
        mPathMap.put(path, file.getName());

        mToolbarLayout = (LinearLayout) mToolbar.getRootView().findViewById(R.id.tree_container);
    }

    private void removeManagerTreeView(String tag) {
        View removeView = mToolbarLayout.findViewWithTag(tag);
        mToolbarLayout.removeView(removeView);
    }

    private void addManagerTreeView(String tag) {
        ManagerTreeView managerTreeView = new ManagerTreeView(this);
        managerTreeView.setName(tag);
        managerTreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().popBackStack((String) v.getTag(),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
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

        // Back 키 눌렀을 때, Toolbar 에 경로를 하나씩 삭제
        if (getSupportFragmentManager().getBackStackEntryCount() > -1) {
            View removeView = mToolbarLayout.getChildAt(mToolbarLayout.getChildCount() - 1);
            removeManagerTreeView((String) removeView.getTag());
        }
    }
}
