
package com.example.suwonsmartapp.androidexam.database.parse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by junsuk on 15. 9. 24..
 */
public class ParseLocalDatabaseActivity extends Activity implements View.OnClickListener {
    ParseObject gameScore = new ParseObject("GameScore");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parse_local_db);

        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_load).setOnClickListener(this);
        findViewById(R.id.btn_sync).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                save();
                break;
            case R.id.btn_load:
                load();
                break;
            case R.id.btn_sync:
                sync();
                break;
        }
    }

    private void sync() {
        gameScore.saveEventually();
    }

    private void save() {
        // Local 에 DB 생성
        gameScore.put("username", "오준석");
        gameScore.put("score", 50000);

        gameScore.pinInBackground();

        Toast.makeText(ParseLocalDatabaseActivity.this, "저장 하였습니다", Toast.LENGTH_SHORT).show();
    }

    private void load() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore");
        query.whereEqualTo("username", "오준석");

        // 로컬로 부터 쿼리
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    // 성공
                    ParseObject parseObject = objects.get(0);
                    int score = parseObject.getInt("score");
                    Toast.makeText(ParseLocalDatabaseActivity.this, "score : " + score,
                            Toast.LENGTH_SHORT).show();
                } else {
                    // 실패
                    Toast.makeText(ParseLocalDatabaseActivity.this, "실패", Toast.LENGTH_SHORT)
                            .show();

                }
            }
        });

    }
}
