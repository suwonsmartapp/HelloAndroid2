
package com.example.suwonsmartapp.androidexam.filemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.filemanager.adapters.FileInfoAdapter;
import com.example.suwonsmartapp.androidexam.filemanager.event.ChangePathEvent;
import com.example.suwonsmartapp.androidexam.filemanager.models.FileInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class FileListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String ARG_PATH = "param1";

    private String mPath;
    private ListView mListView;
    private List<FileInfo> mFileList;
    private Toolbar mToolbar;

    public FileListFragment() {
        // Required empty public constructor
    }

    public static FileListFragment newInstance(String path) {
        FileListFragment fragment = new FileListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PATH, path);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPath = getArguments().getString(ARG_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_file_list, container, false);

        // Toolbar 설정
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

        mListView = (ListView) view.findViewById(R.id.lv_file);

        File file = new File(mPath);

        // 타이틀에 경로 표시
        mToolbar.setTitle(file.getName());

        // 리스트에 파일 목록 표시
        File[] files = file.listFiles();
        mFileList = new ArrayList();
        for (File f : files) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFile(f);
            mFileList.add(fileInfo);
        }

        FileInfoAdapter adapter = new FileInfoAdapter(getActivity(), mFileList);

        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        // mListView.setItemsCanFocus(true);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        File file = mFileList.get(position).getFile();
        if (file.isDirectory()) {
            EventBus.getDefault().post(new ChangePathEvent(file));
        }
    }

}
