
package com.example.suwonsmartapp.androidexam.animation;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.suwonsmartapp.androidexam.R;

import java.util.Random;

public class TransitionDrawableExamActivity extends Activity implements View.OnClickListener {

    private ImageView mImageView;

    private ColorDrawable mColorDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_drawable);

        mImageView = (ImageView) findViewById(R.id.image);
        mImageView.setOnClickListener(this);

        mColorDrawable = new ColorDrawable(Color.WHITE);
        changeColor();
    }

    @Override
    public void onClick(View v) {
        changeColor();
    }

    private void changeColor() {
        ColorDrawable targetDrawable = new ColorDrawable(getRandomColor());

        Drawable[] drawables = new Drawable[] {
                mColorDrawable, targetDrawable
        };
        TransitionDrawable mTransitionDrawable = new TransitionDrawable(drawables);
        mTransitionDrawable.startTransition(500);
        mImageView.setImageDrawable(mTransitionDrawable);

        mColorDrawable = targetDrawable;
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
