
package com.example.suwonsmartapp.androidexam.mission.extra.randomcolorlistview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suwonsmartapp.androidexam.R;

import java.util.List;
import java.util.Random;

/**
 * Created by junsuk on 15. 9. 9..
 * 랜덤 색상 보여주는 Adapter 샘플
 */
public class RandomListAdapter extends BaseAdapter {
    private List<Data> mList;
    private Context mContext;

    public RandomListAdapter(Context context, List<Data> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.item_simple_data3, parent,
                false);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView text1 = (TextView) convertView.findViewById(R.id.tv_text1);
        TextView text2 = (TextView) convertView.findViewById(R.id.tv_text2);

        Data data = mList.get(position);
        imageView.setImageResource(R.mipmap.ic_launcher);
        text1.setText(data.getTextView1());
        text2.setText(data.getTextView2());

        convertView.setBackgroundColor(getRandomColor());

        return convertView;
    }

    private int getRandomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
