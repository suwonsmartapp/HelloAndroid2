
package com.example.suwonsmartapp.androidexam.camera;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.suwonsmartapp.androidexam.R;

/**
 * Created by junsuk on 2015. 11. 6..
 */
public class CameraActivity extends Activity {

    private static final String TAG = CameraActivity.class.getSimpleName();
    private Camera mCamera;
    private CameraPreview mCameraPreview;

    private Window mWindow;
    private WindowManager.LayoutParams mLayoutParams;
    private SeekBar mSeekBar;
    private int mBrightness;

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(findFrontSideCamera()); // attempt to get a Camera
                                                    // instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
            Log.d(TAG, e.getMessage());
        }
        return c; // returns null if camera is unavailable
    }

    // 전면 카메라 ID 얻기
    private static int findFrontSideCamera() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cmInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cmInfo);
            if (cmInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 카메라가 없을 때 처리
        if (!checkCameraHardware(this)) {
            Toast.makeText(CameraActivity.this, "카메라가 없습니다 종료됩니다", Toast.LENGTH_SHORT).show();
            finish();
        }

        setContentView(R.layout.activity_camera);

        // 카메라 인스턴스 얻기
        mCamera = getCameraInstance();
        // 카메라 프리뷰를 보여줄 SurfaceView 상속한 View
        mCameraPreview = new CameraPreview(this, mCamera);

        // 동적으로 View를 추가
        FrameLayout layout = (FrameLayout) findViewById(R.id.suface_view);
        layout.addView(mCameraPreview);

        // 밝기 조절용 SeekBar
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);

        try {
            // 시스템 밝기 가져오기 0 ~ 255
            mBrightness = android.provider.Settings.System.getInt(getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
            Log.d(TAG, mBrightness + "");
        } catch (Settings.SettingNotFoundException e) {

        }

        // 0.0f ~ 1.0f
        Log.d(TAG, getWindow().getAttributes().screenBrightness + "");
        // getWindow().getAttributes().screenBrightness = 1.0f;
        mSeekBar.setProgress(mBrightness);
    }

    /** Check if this device has a camera */
    private boolean checkCameraHardware(Context context) {
        // this device has a camera
        // no camera on this device
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCamera != null) {
            mCamera.release();
        }
    }
}
