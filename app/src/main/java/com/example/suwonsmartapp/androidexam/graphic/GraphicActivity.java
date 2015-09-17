
package com.example.suwonsmartapp.androidexam.graphic;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;

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
        Bitmap bitmap = mShapeView.getDrawingCache();
        mShapeView.setDrawingCacheEnabled(false);
    }
}
