package kz.naimi.interview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.afollestad.materialcamera.MaterialCamera;

public class VideoActivity extends AppCompatActivity {

    MaterialCamera materialCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        materialCamera = new MaterialCamera(this);
        materialCamera.start(6969);
    }
}
