package com.example.suwonsmartapp.androidexam.recyclerview;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.suwonsmartapp.androidexam.R;
import com.suwonsmartapp.abl.AsyncBitmapLoader;

/**
 * Created by junsuk on 2015. 10. 13..
 */
public class LoadPictureAdapter extends RecyclerView.Adapter<LoadPictureAdapter.ViewHolder>
        implements AsyncBitmapLoader.BitmapLoadListener {

    private Context mContext;
    private Cursor mCursor;

    // 다이나믹 비트맵 로더
    private AsyncBitmapLoader mAsyncBitmapLoader;

    public LoadPictureAdapter(Context context, Cursor data) {
        mContext = context;
        mCursor = data;

        // 다이나믹 비트맵 로더 생성 및 이벤트 연결
        mAsyncBitmapLoader = new AsyncBitmapLoader(context);
        mAsyncBitmapLoader.setBitmapLoadListener(this);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 레이아웃 가져오기
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);
        // 홀더에 저장
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 이미지 셋팅
        if (position % 3 == 0) {
            holder.textView.setText("a;lsdkjfa;lsdjkfl;ajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjal;sdjkf");
        } else if (position % 3 == 1) {
            holder.textView.setText("a;lsdkjfa;lsdjkfl;ajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjal;sdjkf");
        } else {
            holder.textView.setText("a;lsdkjfa;lsdjkfl;ajsdlf;kjaajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjajsdlf;kjasl;dfjal;jksdfl;ajksdl;fjl;ajksdl;fjal;sdjkf");
        }
        mAsyncBitmapLoader.loadBitmap(position, holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    @Override
    public long getItemId(int position) {
        mCursor.moveToPosition(position);
        final Cursor cursor = mCursor;
        return cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
    }

    @Override
    public Bitmap getBitmap(int position) {
        // id 가져오기
        long id = getItemId(position);

        // Bitmap 샘플링
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2; // 2의 배수

        // id 로부터 bitmap 생성
        return MediaStore.Images.Thumbnails.getThumbnail(mContext.getContentResolver(),
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
                options);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
