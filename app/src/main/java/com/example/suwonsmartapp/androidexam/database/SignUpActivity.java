
package com.example.suwonsmartapp.androidexam.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.database.helper.UserDbHelper;

/**
 * Created by junsuk on 15. 9. 18..
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNickname;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);

        mNickname = (EditText) findViewById(R.id.edit_nickname);
        mEmail = (EditText) findViewById(R.id.edit_email);
        mPassword = (EditText) findViewById(R.id.edit_password);
        mPasswordVerify = (EditText) findViewById(R.id.edit_password_verify);

        findViewById(R.id.btn_sign_up).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 가입 처리
        if (mPassword.getText().toString().equals(mPasswordVerify.getText().toString()) == false) {
            Toast.makeText(SignUpActivity.this, "패스워드를 확인 해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        UserDbHelper helper = new UserDbHelper(this);

        long inserted = helper.insert(mNickname.getText().toString(),
                mEmail.getText().toString(),
                mPassword.getText().toString());

        if (inserted != -1) {
            Toast.makeText(SignUpActivity.this, "가입 되었습니다. 로그인 해 주세요", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(SignUpActivity.this, "email 이 중복 되었습니다", Toast.LENGTH_SHORT).show();
        }
    }
}
