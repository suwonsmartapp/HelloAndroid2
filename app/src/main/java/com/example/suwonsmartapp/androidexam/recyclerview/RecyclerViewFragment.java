
package com.example.suwonsmartapp.androidexam.recyclerview;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.suwonsmartapp.androidexam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsuk on 15. 9. 25..
 */
public class RecyclerViewFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView mRecyclerView;
    private LoadPictureAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);
    }

    // CursorLoader 를 생성
    // Background Thread 에서 동작
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getActivity(),
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    // onCreateLoader 에서 작업이 끝난 후에 호출 됨
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<Integer> imageIdList = new ArrayList<>();
        while (data.moveToNext()) {
            imageIdList.add(data.getInt(data.getColumnIndexOrThrow("_id")));
        }

        mAdapter = new LoadPictureAdapter(getContext(), imageIdList);
        mRecyclerView.setAdapter(mAdapter);
    }

    // 데이타 리셋 처리
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
