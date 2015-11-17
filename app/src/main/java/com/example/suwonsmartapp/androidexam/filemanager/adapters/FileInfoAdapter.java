package com.example.suwonsmartapp.androidexam.filemanager.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.filemanager.models.FileInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by junsuk on 15. 11. 17..
 */
public class FileInfoAdapter extends BaseAdapter implements View.OnClickListener {

    private static final String TAG = FileInfoAdapter.class.getSimpleName();
    private final List<FileInfo> mList;
    private final Context mContext;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yy.MM.dd. a KK:mm");

    public FileInfoAdapter(Context context, List<FileInfo> list) {
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
        ViewHolder holder;

        // Layout 을 완성하고 ViewHolder와 연결
        // 이유 : findViewById를 한번만 하려고
        if (convertView == null) {
            holder = new ViewHolder();

            // 처음 로드
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_file_info, parent,
                    false);

            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_check);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.path = (TextView) convertView.findViewById(R.id.tv_path);
            holder.time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.permission = (TextView) convertView.findViewById(R.id.tv_permission);
            holder.capacity = (TextView) convertView.findViewById(R.id.tv_capacity);

            convertView.setTag(holder);
        } else {
            // 재사용
            holder = (ViewHolder) convertView.getTag();
        }

        // Data 를 Layout 에 설정
        FileInfo fileInfo = mList.get(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "checked");
            }
        });

        // 아이콘
        int resId = 0;
        if (fileInfo.getFile().isDirectory()) {
            resId = R.mipmap.ic_launcher;
        } else {
            resId = R.drawable.com_facebook_button_icon;
        }
        holder.icon.setImageResource(resId);

        // path
        holder.path.setText(fileInfo.getFile().getName());

        // time
        Date date = new Date(fileInfo.getFile().lastModified());
        holder.time.setText(mFormat.format(date));

        // permission
        String permission = "";
        if (fileInfo.getFile().canRead()) {
            permission += "r";
        } else {
            permission += "-";
        }
        if (fileInfo.getFile().canWrite()) {
            permission += "w";
        } else {
            permission += "-";
        }
        if (fileInfo.getFile().canExecute()) {
            permission += "x";
        } else {
            permission += "-";
        }
        holder.permission.setText(permission);

        // 용량 (MB)
        if (fileInfo.getFile().isDirectory()) {
            holder.capacity.setText("");
        } else {
            holder.capacity.setText((fileInfo.getFile().getTotalSpace() / 1024 / 1024) + " MB");
        }

        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "item click", Toast.LENGTH_SHORT).show();
    }


    static class ViewHolder {
        CheckBox checkBox;
        ImageView icon;
        TextView path;
        TextView time;
        TextView permission;
        TextView capacity;

    }

}
