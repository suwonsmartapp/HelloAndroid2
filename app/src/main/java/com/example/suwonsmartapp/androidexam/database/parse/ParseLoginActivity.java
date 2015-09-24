
package com.example.suwonsmartapp.androidexam.database.parse;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.database.SignUpActivity;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by junsuk on 15. 9. 18.. DB 연습 - 로그인 Activity
 */
public class ParseLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmail;
    private EditText mPassword;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mCheckBox = (CheckBox) findViewById(R.id.check_email);

        findViewById(R.id.tv_sign_up).setOnClickListener(this);
        findViewById(R.id.btn_login).setOnClickListener(this);

        // http://developer.android.com/intl/ko/training/basics/data-storage/shared-preferences.html#GetSharedPreferences
        mEmail.setText(loadEmail());
    }

    private String loadEmail() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("pref_email", "");
        return email;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.btn_login:
                ParseUser.logInInBackground(mEmail.getText().toString(),
                        mPassword.getText().toString(),
                        new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                    // 로그인 성공
                                    Toast.makeText(ParseLoginActivity.this, "성공",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // 로그인 실패
                                    Toast.makeText(ParseLoginActivity.this, "실패",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
        }
    }

    private void saveEmail(String email) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("pref_email", email);
        // editor.commit(); // 동기식 sync
        editor.apply(); // 비동기 async
    }
}
