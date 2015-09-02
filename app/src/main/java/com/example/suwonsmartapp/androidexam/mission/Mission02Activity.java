
package com.example.suwonsmartapp.androidexam.mission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;

public class Mission02Activity extends AppCompatActivity {

    private EditText mMessageEditText;
    private TextView mByteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission02);

        mMessageEditText = (EditText) findViewById(R.id.message_edit_text);
        mByteTextView = (TextView) findViewById(R.id.byte_text_view);

        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.toString().getBytes().length;
                mByteTextView.setText(length + " / 80 바이트");
            }
        });

        findViewById(R.id.send_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), mMessageEditText.getText(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
