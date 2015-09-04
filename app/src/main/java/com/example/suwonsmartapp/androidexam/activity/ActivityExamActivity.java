
package com.example.suwonsmartapp.androidexam.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.suwonsmartapp.androidexam.R;

public class ActivityExamActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mNameEditText;
    private EditText mPhoneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_exam);

        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
        mPhoneEditText = (EditText) findViewById(R.id.phone_edit_text);

        findViewById(R.id.btn1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TargetActivity 로 이동
        Intent intent = new Intent(getApplicationContext(), TargetActivity.class);

        // Data 추가
        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhoneEditText.getText().toString());

        startActivity(intent);
    }
}
