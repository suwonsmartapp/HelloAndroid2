
package com.example.suwonsmartapp.androidexam.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.suwonsmartapp.androidexam.R;

/**
 * Created by junsuk on 15. 9. 8..
 */
public class AnimationActivity extends Activity implements View.OnClickListener {

    private Animation mScaleAndRotateAnimation;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);

        // 버튼 이벤트
        findViewById(R.id.btnStart).setOnClickListener(this);

        mImageView = (ImageView) findViewById(R.id.ivImage);

        // 애니메이션
        mScaleAndRotateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this,
                R.anim.anim_scale_rotate);

        // 애니메이션 시작, 끝, 반복 할 때 처리를 구현
        mScaleAndRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        mImageView.startAnimation(mScaleAndRotateAnimation);
    }
}
