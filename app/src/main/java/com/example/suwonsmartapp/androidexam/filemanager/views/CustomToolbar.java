
package com.example.suwonsmartapp.androidexam.filemanager.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.suwonsmartapp.androidexam.filemanager.event.AddTreeViewEvent;

import java.io.File;

import de.greenrobot.event.EventBus;

/**
 * Created by junsuk on 15. 11. 25..
 */
public class CustomToolbar extends Toolbar {

    private final Context mContext;
    private LinearLayout mToolbarLayout;

    public CustomToolbar(Context context) {
        this(context, null);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        init();
    }

    // res/layout/layout_custom_toolbar.xml 를 하드코딩으로 작성
    private void init() {
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(mContext);
        ViewGroup.LayoutParams horizontalScrollViewParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        horizontalScrollView.setLayoutParams(horizontalScrollViewParams);

        mToolbarLayout = new LinearLayout(mContext);
        ViewGroup.LayoutParams linearLayoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mToolbarLayout.setLayoutParams(linearLayoutParams);
        mToolbarLayout.setOrientation(LinearLayout.HORIZONTAL);

        horizontalScrollView.addView(mToolbarLayout);

        addView(horizontalScrollView);
    }

    public void removeManagerTreeView(View removeView) {
        mToolbarLayout.removeView(removeView);
    }

    public void removeLastManagerTreeView() {
        ManagerTreeView removeView = (ManagerTreeView) mToolbarLayout.getChildAt(mToolbarLayout
                .getChildCount() - 1);
        removeManagerTreeView(removeView);
    }

    public void addManagerTreeView(String tag) {
        ManagerTreeView managerTreeView = new ManagerTreeView(mContext);
        managerTreeView.setName(tag);
        managerTreeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new AddTreeViewEvent((String) v.getTag()));

                // Toolbar 에서 현재 클릭 된 경로 이후를 삭제
                for (int i = mToolbarLayout.getChildCount() - 1; i > 0; i--) {
                    ManagerTreeView childView = (ManagerTreeView) mToolbarLayout.getChildAt(i);
                    String tag = (String) v.getTag();
                    if (childView.getName().equals(tag) == false) {
                        mToolbarLayout.removeView(childView);
                    } else {
                        break;
                    }
                }
            }
        });

        File file = new File(tag);
        managerTreeView.setTitle(file.getName());
        mToolbarLayout.addView(managerTreeView);
    }
}
