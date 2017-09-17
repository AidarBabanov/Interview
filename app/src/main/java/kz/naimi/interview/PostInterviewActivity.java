package kz.naimi.interview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class PostInterviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_interview);
        PostUploadCommands postUploadCommands = new PostUploadCommands();
        boolean allUploaded = false;

        postUploadCommands.execute();
    }
}
