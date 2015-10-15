package com.example.suwonsmartapp.androidexam.swiperefreshlayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.suwonsmartapp.androidexam.R;

import java.util.ArrayList;
import java.util.List;

public class SwipeRefreshLayoutFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView mListView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private List<String> mList;
    private ArrayAdapter<String> mAdapter;

    public SwipeRefreshLayoutFragment() {
        // Required empty public constructor
    }

    public static SwipeRefreshLayoutFragment newInstance() {
        SwipeRefreshLayoutFragment fragment = new SwipeRefreshLayoutFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_swipe_refresh_layout, container, false);
        mListView = (ListView) view.findViewById(R.id.list_view);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mList = new ArrayList<>();
        mList.add("이경범");
        mList.add("최동형");
        mList.add("오준석");

        mAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,
                mList);

        mListView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        try {
            Thread.sleep(3000);

            mList.add("허수인");
            mList.add("최푸름");
            mList.add("허수인");
            mList.add("허수인");
            mList.add("허수인");

            mAdapter.notifyDataSetChanged();


            mSwipeRefreshLayout.setEnabled(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
