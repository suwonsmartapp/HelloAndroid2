
package com.example.suwonsmartapp.androidexam;

/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.suwonsmartapp.androidexam.activity.ActivityExamActivity;
import com.example.suwonsmartapp.androidexam.activity.ParcelableActivity;
import com.example.suwonsmartapp.androidexam.activity.WebActivity;
import com.example.suwonsmartapp.androidexam.animation.AnimationActivity;
import com.example.suwonsmartapp.androidexam.calendar.CalendarActivity;
import com.example.suwonsmartapp.androidexam.calendar2.Calendar2Activity;
import com.example.suwonsmartapp.androidexam.database.LogInActivity;
import com.example.suwonsmartapp.androidexam.database.parse.ParseLocalDatabaseActivity;
import com.example.suwonsmartapp.androidexam.database.parse.ParseLoginActivity;
import com.example.suwonsmartapp.androidexam.drawer.NavigationDrawerActivity;
import com.example.suwonsmartapp.androidexam.fragment.FragmentActivity;
import com.example.suwonsmartapp.androidexam.graphic.GraphicActivity;
import com.example.suwonsmartapp.androidexam.layout.FrameLayoutActivity;
import com.example.suwonsmartapp.androidexam.mission.Mission01Activity;
import com.example.suwonsmartapp.androidexam.mission.Mission02Activity;
import com.example.suwonsmartapp.androidexam.mission.extra.TransitionDrawableExamActivity;
import com.example.suwonsmartapp.androidexam.mission.extra.randomcolorlistview.RandomColorListActivity;
import com.example.suwonsmartapp.androidexam.musicplayer.MusicActivity;
import com.example.suwonsmartapp.androidexam.parsing.json.WeatherActivity;
import com.example.suwonsmartapp.androidexam.provider.ContactLoaderActivity;
import com.example.suwonsmartapp.androidexam.provider.LoadPictureActivity;
import com.example.suwonsmartapp.androidexam.receiver.BroadcastActivity;
import com.example.suwonsmartapp.androidexam.thread.ThreadActivity;
import com.example.suwonsmartapp.androidexam.viewpager.ScreenSlideActivity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, getData(),
                android.R.layout.simple_list_item_1, new String[] {
                        "title"
                },
                new int[] {
                        android.R.id.text1
                }));
        getListView().setTextFilterEnabled(true);
    }

    protected List<Map<String, Object>> getData() {
        List<Map<String, Object>> myData = new ArrayList<>();

        // 메뉴 추가 부분
        addItem(myData, "TransitionDrawable", TransitionDrawableExamActivity.class);
        addItem(myData, "FrameLayout", FrameLayoutActivity.class);
        addItem(myData, "도전01. 한 화면에 두 개의 이미지뷰 배치하기", Mission01Activity.class);
        addItem(myData, "도전02. SMS 입력 화면 만들고 글자수 표시하기", Mission02Activity.class);
        addItem(myData, "화면이동 예제", ActivityExamActivity.class);
        addItem(myData, "WebView 연습", WebActivity.class);
        addItem(myData, "Animation 연습", AnimationActivity.class);
        addItem(myData, "달력 만들기", CalendarActivity.class);
        addItem(myData, "달력 (Android 내장)", Calendar2Activity.class);
        addItem(myData, "Extra Mission - 랜덤색상ListView", RandomColorListActivity.class);
        addItem(myData, "Thread", ThreadActivity.class);
        addItem(myData, "JSON 파싱 - 날씨정보", WeatherActivity.class);
        addItem(myData, "Fragment", FragmentActivity.class);
        addItem(myData, "ViewPager", ScreenSlideActivity.class);
        addItem(myData, "BroadcastReceiver", BroadcastActivity.class);
        addItem(myData, "Graphic", GraphicActivity.class);
        addItem(myData, "Database", LogInActivity.class);
        addItem(myData, "Parcelable 전송", ParcelableActivity.class);
        addItem(myData, "Parse 로그인", ParseLoginActivity.class);
        addItem(myData, "Parse 로컬 DB", ParseLocalDatabaseActivity.class);
        addItem(myData, "content provider, Loader - 연락처 ", ContactLoaderActivity.class);
        addItem(myData, "content provider, Loader - 사진 ", LoadPictureActivity.class);
        addItem(myData, "Service - Music Player", MusicActivity.class);
        addItem(myData, "Navigation Drawer", NavigationDrawerActivity.class);
        // ----- 메뉴 추가 여기까지

        // 이름 순 정렬
        // Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<>();
        temp.put("title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    protected void addItem(List<Map<String, Object>> data, String name, Class cls) {
        this.addItem(data, name, new Intent(this, cls));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String, Object> map = (Map<String, Object>) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}
