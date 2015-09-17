
package com.example.suwonsmartapp.androidexam.graphic;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by junsuk on 15. 9. 17..
 */
public class GraphicActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ShapeView shapeView = new ShapeView(this);
        setContentView(shapeView);

        // shapeView.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // Toast.makeText(GraphicActivity.this, "클릭",
        // Toast.LENGTH_SHORT).show();
        //
        // // shapeView.startDrag(null, new View.DragShadowBuilder(shapeView),
        // null, 0);
        // }
        // });
        //
        // shapeView.setOnTouchListener(new View.OnTouchListener() {
        // @Override
        // public boolean onTouch(View v, MotionEvent event) {
        // Log.d("ShapeView", "onTouchEvent");
        // if (event.getAction() == MotionEvent.ACTION_MOVE) {
        // shapeView.startDrag(null, new View.DragShadowBuilder(shapeView),
        // shapeView, 0);
        // }
        // return true;
        // }
        // });

    }
}
