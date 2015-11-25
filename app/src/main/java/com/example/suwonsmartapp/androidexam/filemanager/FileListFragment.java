
package com.example.suwonsmartapp.androidexam.filemanager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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
    private static final String TAG = FileListFragment.class.getSimpleName();

    private String mPath;
    private ListView mListView;
    private List<FileInfo> mFileList;

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

    public static String getMimeType(String url) {
        String type = null;

        // 파일명에 공백이 있을 때 확장자를 못 가져오는 이슈 해결책
        // https://code.google.com/p/android/issues/detail?id=5510
        String text = url.substring(url.lastIndexOf("."));
        String extension = MimeTypeMap.getFileExtensionFromUrl(text);

        // String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            type = mime.getMimeTypeFromExtension(extension.toLowerCase());
        }
        return type;
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

        for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
            FragmentManager.BackStackEntry entry = getFragmentManager().getBackStackEntryAt(i);
            Log.d(TAG, entry.toString());
        }
        Log.d(TAG, "=====================");

        View view = inflater.inflate(R.layout.fragment_file_list, container, false);

        mListView = (ListView) view.findViewById(R.id.lv_file);

        File file = new File(mPath);

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
            // 폴더일 경우
            EventBus.getDefault().post(new ChangePathEvent(file));
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), getMimeType(file.getAbsolutePath()));
            Intent chooserIntent = Intent.createChooser(intent, "파일 선택..");

            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(chooserIntent);
            } else {
                Toast.makeText(getActivity(), "수행할 수 있는 앱이 없습니다", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
