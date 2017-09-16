package kz.naimi.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private Button startInterviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        descriptionTextView = (TextView) findViewById(R.id.description_textView);
        startInterviewButton = (Button) findViewById(R.id.start_interview_button);

        startInterviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startInterview();
            }
        });
    }

    private void startInterview(){
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}
