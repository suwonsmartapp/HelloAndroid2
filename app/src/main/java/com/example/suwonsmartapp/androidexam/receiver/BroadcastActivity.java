
package com.example.suwonsmartapp.androidexam.receiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by junsuk on 15. 9. 16..
 */
public class BroadcastActivity extends Activity implements View.OnClickListener {
    MyReceiver mReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("브로드 캐스트 발송");
        button.setOnClickListener(this);

        LinearLayout linearLayout = new LinearLayout(this);
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);

        linearLayout.addView(button);

        setContentView(linearLayout);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        sendBroadcast(intent);

        // 순서대로 Broadcast 쏘기
        // sendOrderedBroadcast();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // MyReceiver 가 받을 Intent 정의
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.MY_BROADCAST");
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);

        // local 브로드캐스트 리시버 등록
        registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mReceiver);
    }
}
