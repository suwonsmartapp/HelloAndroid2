
package com.example.suwonsmartapp.androidexam.mission;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.suwonsmartapp.androidexam.R;

public class Mission01Activity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mImageView1;
    private ImageView mImageView2;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission01);

        mImageView1 = (ImageView) findViewById(R.id.image1);
        mImageView2 = (ImageView) findViewById(R.id.image2);

        findViewById(R.id.upBtn).setOnClickListener(this);
        findViewById(R.id.downBtn).setOnClickListener(this);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl);
        mImageView1.setImageBitmap(mBitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upBtn:
                mImageView1.setImageBitmap(mBitmap);
                mImageView2.setImageBitmap(null);
                break;
            case R.id.downBtn:
                mImageView1.setImageBitmap(null);
                mImageView2.setImageBitmap(mBitmap);
                break;
        }
    }
}
