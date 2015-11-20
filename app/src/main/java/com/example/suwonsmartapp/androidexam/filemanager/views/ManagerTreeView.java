
package com.example.suwonsmartapp.androidexam.filemanager.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.suwonsmartapp.androidexam.R;

/**
 * Created by junsuk on 15. 11. 19..
 */
public class ManagerTreeView extends LinearLayout {
    private final Context mContext;
    private TextView mTitleTextView;

    public ManagerTreeView(Context context) {
        this(context, null);
    }

    public ManagerTreeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ManagerTreeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        init();
    }

    private void init() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_manager_tree, this);
        mTitleTextView = (TextView) rootView.findViewById(R.id.tv_title);
    }

    public void setOnClickListener(OnClickListener listener) {
        mTitleTextView.setOnClickListener(listener);
    }

    public void setTitle(String title) {
        mTitleTextView.setText(title);
    }
}
