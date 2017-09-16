package kz.naimi.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button startRecordingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionTextView = (TextView) findViewById(R.id.question_textView);
        startRecordingButton = (Button) findViewById(R.id.start_recording_button);

        startRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecording();
            }
        });
    }
    private void startRecording(){
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
}