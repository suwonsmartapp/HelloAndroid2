
package com.example.suwonsmartapp.androidexam.database;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.database.contract.UserContract;
import com.example.suwonsmartapp.androidexam.database.helper.UserDbHelper;

/**
 * Created by junsuk on 15. 9. 18.. DB 연습 - 로그인 Activity
 */
public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDbHelper mUserDbHelper;
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);

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

                UserDbHelper helper = new UserDbHelper(this);
                Cursor cursor = helper.query();

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (cursor.moveToNext()) {
                        String email = cursor
                                .getString(
                                        cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_EMAIL)
                                );

                        String password = cursor
                                .getString(
                                        cursor.getColumnIndexOrThrow(UserContract.UserEntry.COLUMN_NAME_PASSWORD)
                                );

                        if (email.equals(mEmail.getText().toString()) &&
                                password.equals(mPassword.getText().toString())) {
                            Toast.makeText(LogInActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                Toast.makeText(LogInActivity.this, "이메일 또는 패스워드가 틀렸습니다", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
