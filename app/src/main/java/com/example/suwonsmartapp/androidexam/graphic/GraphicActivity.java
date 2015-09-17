
package com.example.suwonsmartapp.androidexam.graphic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;
import com.example.suwonsmartapp.androidexam.utils.storage.StorageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by junsuk on 15. 9. 17..
 */
public class GraphicActivity extends AppCompatActivity {

    private ShapeView mShapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mShapeView = new ShapeView(this);
        setContentView(mShapeView);
    }

    // 메뉴 생성 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    // 메뉴를 클릭 했을 때 처리
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void save() {
        Toast.makeText(GraphicActivity.this, "save", Toast.LENGTH_SHORT).show();
        mShapeView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(mShapeView.getDrawingCache());
        mShapeView.setDrawingCacheEnabled(false);

        // 외부 저장소에 접근이 가능하면, 파일 생성
        if (StorageUtil.isExternalStorageWritable()) {

            File file = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                    "pictureTest.jpg"
                    );

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                Toast.makeText(GraphicActivity.this, "저장 되었습니다", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 미디어 스캔을 강제로 하도록 브로드캐스트를 발송
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://" + file.toString())));

        } else {
            Toast.makeText(GraphicActivity.this, "메모리를 사용할 수 없습니다", Toast.LENGTH_SHORT).show();
        }
    }
}
