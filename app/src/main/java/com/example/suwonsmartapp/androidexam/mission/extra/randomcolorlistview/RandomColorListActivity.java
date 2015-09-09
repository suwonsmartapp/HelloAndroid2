package com.example.suwonsmartapp.androidexam.mission.extra.randomcolorlistview;

import android.app.ListActivity;
import android.os.Bundle;

import com.example.suwonsmartapp.androidexam.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsuk on 15. 9. 9..
 * Extra 미션 - 랜덤 색상 리스트 뷰
 */
public class RandomColorListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Data> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(new Data(R.mipmap.ic_launcher, "Title", "내용"));
        }

        RandomListAdapter adapter = new RandomListAdapter(this, dataList);

        setListAdapter(adapter);
    }
}
