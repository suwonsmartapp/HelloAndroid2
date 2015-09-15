
package com.example.suwonsmartapp.androidexam.parsing.json;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.utils.network.NetworkUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsuk on 15. 9. 14.. 날씨 정보 보여주는 앱
 */
public class WeatherActivity extends Activity implements View.OnKeyListener {

    private static final String TAG = WeatherActivity.class.getSimpleName();

    // 날씨 예보 제공 URL
    private static final String URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast?units=metric&q=";

    private EditText mCityEditText;
    private ListView mWeatherListView;
    private WeatherAdapter mAdapter;

    private ProgressBar mProgressBar;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mWeatherListView.setAdapter(mAdapter);
            mProgressBar.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);

        mCityEditText = (EditText) findViewById(R.id.et_city);
        mWeatherListView = (ListView) findViewById(R.id.lv_weather);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        mCityEditText.setOnKeyListener(this);

        // Android 4.0 부터 네트워킹 제약
        showWeatherInfo();
    }

    public void showWeatherInfo() {
        mProgressBar.setVisibility(View.VISIBLE);

        final String city = mCityEditText.getText().toString();

        // 네트워킹 처리는 반드시 Thread 에서 해야 한다!!!
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    // HTTP 에서 내용을 String 으로 받아 온다
                    String jsonString = NetworkUtil.getReturnString(URL_FORECAST + city);

                    // 받아온 JSON String 을 JSON Object로 변환한다
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    // 날씨 정보 저장할 리스트
                    List<Weather> weatherList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        String time = object.getString("dt_txt");
                        time = time.split(" ")[1].substring(0, 5);
                        String temp = object.getJSONObject("main").getString("temp");
                        String description = object.getJSONArray("weather")
                                .getJSONObject(0).getString("description");

                        weatherList.add(new Weather(time, temp, description));
                    }

                    mAdapter = new WeatherAdapter(WeatherActivity.this, weatherList);

                    // UI 갱신을 핸들러에 요청
                    mHandler.sendEmptyMessage(0);

                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }).start();

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            showWeatherInfo();
            return true;
        }

        return false;
    }
}
