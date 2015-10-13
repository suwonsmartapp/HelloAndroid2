
package com.example.suwonsmartapp.androidexam.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.suwonsmartapp.androidexam.R;

import java.util.Random;

/**
 * Created by junsuk on 15. 9. 15..
 */
public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private ColorFragment mColorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mColorFragment = (ColorFragment) getSupportFragmentManager().findFragmentById(
                R.id.frag_color);

        findViewById(R.id.button).setOnClickListener(this);
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public void onClick(View v) {
//        mColorFragment.setColor(getRandomColor());
    }
}
