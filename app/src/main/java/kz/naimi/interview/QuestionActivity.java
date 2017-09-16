package kz.naimi.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.materialcamera.MaterialCamera;

import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    private final static int CAMERA_RQ = 6969;

    private TextView questionTextView;
    private Button startRecordingButton;

    private InterviewElement interviewElement;
    private List<InterviewElement> interview;
    private Integer currentQuestion;

    private MaterialCamera materialCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        questionTextView = (TextView) findViewById(R.id.question_textView);
        startRecordingButton = (Button) findViewById(R.id.start_recording_button);


        materialCamera = new MaterialCamera(this);
        materialCamera.allowRetry(false);
        materialCamera.autoSubmit(true);
        materialCamera.showPortraitWarning(false);
        materialCamera.defaultToFrontFacing(true);
        materialCamera.countdownImmediately(true);
        materialCamera.countdownMinutes(2.09f);
        materialCamera.autoRecordWithDelayMs(5000);

        startRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                materialCamera.start(CAMERA_RQ);
            }
        });

        currentQuestion = Integer.parseInt(getIntent().getStringExtra(Intent.EXTRA_TEXT));

        interview = SingletonVariableShare.getInstance().getInterview();
        Log.i("interview", interview.toString());
        interviewElement = interview.get(currentQuestion);
        questionTextView.setText(interviewElement.getQuestion());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Received recording or error from MaterialCamera
        if (requestCode == CAMERA_RQ) {

            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Saved to: " + data.getDataString(), Toast.LENGTH_LONG).show();
                interviewElement.setVideoAnswer(data.getDataString());
                interview.set(currentQuestion, interviewElement);
                if(currentQuestion+1<interview.size())startNewQuestion();
                else startPostInterviewActivity();

            } else if(data != null) {
                Exception e = (Exception) data.getSerializableExtra(MaterialCamera.ERROR_EXTRA);
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void startNewQuestion(){
        Intent intent = new Intent(this, QuestionActivity.class);
        SingletonVariableShare.getInstance().setInterview(interview);
        int nextQuestion = currentQuestion+1;
        intent.putExtra(Intent.EXTRA_TEXT, nextQuestion+"");
        startActivity(intent);
    }
    private void startPostInterviewActivity(){
        Intent intent = new Intent(this, PostInterviewActivity.class);
        startActivity(intent);
    }

}