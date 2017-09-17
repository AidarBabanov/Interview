package kz.naimi.interview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView descriptionTextView;
    private Button startInterviewButton;

    private List<InterviewElement> interview;
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

        interview = new LinkedList<>();
        InterviewElement ie1 = new InterviewElement();
        ie1.setQuestion("Сколько шариков для\nгольфа поместится\nв школьный автобус?");
        interview.add(ie1);
        InterviewElement ie2 = new InterviewElement();
        ie2.setQuestion("Сколько настройщиков\nпианино во всем мире?");
        interview.add(ie2);
        InterviewElement ie3 = new InterviewElement();
        ie3.setQuestion("Почему крышка люка\nкруглая?");
        interview.add(ie3);
    }

    private void startInterview(){
        Intent intent = new Intent(this, QuestionActivity.class);
        SingletonVariableShare.getInstance().setInterview(interview);
        intent.putExtra(Intent.EXTRA_TEXT, 0+"");
        startActivity(intent);
    }
}
