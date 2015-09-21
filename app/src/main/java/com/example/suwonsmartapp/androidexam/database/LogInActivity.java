
package com.example.suwonsmartapp.androidexam.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.database.helper.UserDbHelper;

/**
 * Created by junsuk on 15. 9. 18.. DB 연습 - 로그인 Activity
 */
public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDbHelper mUserDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        findViewById(R.id.tv_sign_up).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        mUserDbHelper = new UserDbHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btn_login:
                // Todo 로그인 처리
                // long insertedId = mUserDbHelper.insert("test", "test",
                // "test");

                int count = mUserDbHelper.update("test", "테스트");
                if (count != 0) {
                    Toast.makeText(LogInActivity.this, "Update 성공 : " + count, Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
