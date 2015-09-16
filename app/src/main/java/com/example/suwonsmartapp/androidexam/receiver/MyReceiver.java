
package com.example.suwonsmartapp.androidexam.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "배터리가 부족합니다", Toast.LENGTH_SHORT).show();

            // 내 이후에는 아무도 받지마
            abortBroadcast();

        } else if (intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            Toast.makeText(context, "비행기 모드로 변경 되었습니다", Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals("android.intent.action.MY_BROADCAST")) {
            Toast.makeText(context, "마이 브로드캐스트를 수신 했습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
