
package com.example.suwonsmartapp.androidexam.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.suwonsmartapp.androidexam.R;

import java.util.Calendar;
import java.util.List;

/**
 * Created by junsuk on 15. 9. 8.. 달력 표시용 어댑터
 */
public class CalendarAdapter extends BaseAdapter {

    private List<Calendar> mList;
    private Context mContext;

    public CalendarAdapter(Context context, List<Calendar> list) {
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

    // 아이템이 화면에 보일 때 호출 됨
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // Layout 을 완성하고 ViewHolder와 연결
        // 이유 : findViewById를 한번만 하려고
        if (convertView == null) {
            holder = new ViewHolder();

            // 처음 로드
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, parent);

            holder.dateTextView = (TextView) convertView.findViewById(R.id.tv_date);

            convertView.setTag(holder);
        } else {
            // 재사용
            holder = (ViewHolder) convertView.getTag();
        }

        // Data 를 Layout 에 설정
        Calendar calendar = mList.get(position);
        holder.dateTextView.setText(calendar.get(Calendar.DATE));

        return convertView;
    }

    static class ViewHolder {
        TextView dateTextView;
    }
}
