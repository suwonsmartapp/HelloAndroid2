
package com.example.suwonsmartapp.androidexam.filemanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;

public class FileManagerActivity extends AppCompatActivity {

    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manager);

        mPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(FileManagerActivity.this, mPath, Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, FileListFragment.newInstance(mPath))
                .addToBackStack(null)
                .commit();
    }
}
