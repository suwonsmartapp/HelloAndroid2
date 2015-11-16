
package com.example.suwonsmartapp.androidexam.filemanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.suwonsmartapp.androidexam.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileListFragment extends Fragment {
    private static final String ARG_PATH = "param1";

    private String mPath;
    private ListView mListView;

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

        mListView = (ListView) view.findViewById(R.id.lv_file);

        File file = new File(mPath);
        File[] files = file.listFiles();

        List<String> fileList = new ArrayList();
        for (File f : files) {
            fileList.add(f.isDirectory() + ", " + f.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                fileList);

        mListView.setAdapter(adapter);

        return view;
    }

}
