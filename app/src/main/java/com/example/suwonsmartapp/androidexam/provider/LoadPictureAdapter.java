
package com.example.suwonsmartapp.androidexam.provider;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.suwonsmartapp.androidexam.R;

/**
 * Created by junsuk on 15. 9. 25..
 */
public class LoadPictureAdapter extends CursorAdapter {

    private final LayoutInflater mLayoutInflater;

    public LoadPictureAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);

        mLayoutInflater = LayoutInflater.from(context);
    }

    // 맨 처음 레이아웃 만드는 부분
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // 레이아웃 가져오기
        View view = mLayoutInflater.inflate(R.layout.item_picture, parent, false);

        // 홀더에 저장
        ViewHolder holder = new ViewHolder();
        holder.imageView = (ImageView) view.findViewById(R.id.imageView);
        view.setTag(holder);

        return view;
    }

    // 실제 데이타 셋팅
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        // id 가져오기
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID));

        // Bitmap 샘플링
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 1; // 2의 배수

        // id 로부터 bitmap 생성
        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(),
                id,
                MediaStore.Images.Thumbnails.MINI_KIND,
                options);

        // 이미지 셋팅
        holder.imageView.setImageBitmap(bitmap);
    }

    static class ViewHolder {
        ImageView imageView;
    }

}
