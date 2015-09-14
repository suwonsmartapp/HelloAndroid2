
package com.example.suwonsmartapp.androidexam.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.suwonsmartapp.androidexam.R;

/**
 * Created by junsuk on 15. 9. 11.. 쓰레드
 */
public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = ThreadActivity.class.getSimpleName();

    private Button mThread1Btn;
    private Button mThread2Btn;

    private TextView mNumberTextView1;
    private TextView mNumberTextView2;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_thread);

        mThread1Btn = (Button) findViewById(R.id.btn_thread1);
        mThread2Btn = (Button) findViewById(R.id.btn_thread2);

        mNumberTextView1 = (TextView) findViewById(R.id.tv_number1);
        mNumberTextView2 = (TextView) findViewById(R.id.tv_number2);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        mThread1Btn.setOnClickListener(this);
        mThread2Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thread1:
                progressDialogExam();

                // 굉장히 오래 걸리는 처리 (10초)
                // for (...)

                // 완료되었습니다.

                break;
            case R.id.btn_thread2:

                new DownloadTask().execute();
                break;

        }
    }

    private void progressDialogExam() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("다운로드 중");
        progressDialog.setCancelable(false); // 뒤로 가기로 캔슬되는 것 막기
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 2초 동안 다운로드
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 다운로드 끝나면 progressDialog를 닫는다
                progressDialog.dismiss();
            }
        }).start();

        Log.d(TAG, "ttt");
    }

    // 백그라운드 처리는 바로바로 보이지만, UI 변경은 스레드 종료 시 마지막 결과만 보여진다
    private void runOnUiThreadExam() {
        // UI Thread 로 동작하게 해 주는 Activity 제공 메소드
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작 하는 부분
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리

                    }
                    Log.d(TAG, "" + i); // background
                    mNumberTextView1.setText("" + i); // foreground
                }
            }
        });
    }

    private void threadAndHandler() {
        // Handler 클래스 상속을 생략 한 것

        // 보이는 부분에서 동작하는 Thread
        // UI Thread
        // Foreground Thread
        // Main Thread
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                mNumberTextView1.setText("" + msg.arg1);
            }
        };

        // Thread 클래스 상속을 생략 한 것

        // 안 보이는 부분에서 동작하는 Thread
        // Thread
        // Background Thread
        // Worker Thread
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작 하는 부분
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리

                    }

                    Message msg = new Message();
                    msg.arg1 = i;
                    handler.sendMessage(msg);
                }
            }
        });
        thread.start();
    }

    // 스레드 사용 방법 1
    // background 에서 동작
    private void backgroundThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 스레드로 동작 하는 부분
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000); // 스레드가 잠시 쉰다 1초
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // 에러 처리

                    }
                    Log.d(TAG, "" + i);
                }
            }
        });
        thread.start();
    }

    private class DownloadTask extends AsyncTask<Void, Integer, Void> {
        private AlertDialog.Builder mmBuilder;

        // UI Thread
        // doInBackground 전에 호출 됨
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mmBuilder = new AlertDialog.Builder(ThreadActivity.this);
            mmBuilder.setMessage("다운로드가 완료 되었습니다");
            mmBuilder.setNegativeButton("닫기", null);

            mProgressBar.setProgress(0);
        }

        // Background 쓰레드
        @Override
        protected Void doInBackground(Void... params) {
            // 다운로드 처리
            for (int i = 0; i < 100; i++) {
                // 0.2초 쉬고
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    Log.e(TAG, e.getMessage());
                }

                // onProgressUpdate를 호출
                publishProgress(i + 1);
            }
            return null;
        }

        // UI Thread
        // doInBackground 에서 publishProgress 로 호출하면 호출 됨
        // 직접 호출 하지 않는 이유 : 죽으니까
        // http://developer.android.com/intl/ko/reference/android/os/AsyncTask.html
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mProgressBar.setProgress(values[0]);
            mNumberTextView2.setText(values[0] + "%");
        }

        // UI Thread
        // doInBackground 가 수행 된 후에 호출 됨
        // doInBackground 에서 return 된 값이 파라메터로 넘어 옴
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mmBuilder.show();
        }
    }
}
