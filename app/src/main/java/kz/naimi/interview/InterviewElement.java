package kz.naimi.interview;

public class InterviewElement {
    private String question;
    private String videoAnswer;
    private boolean uploaded = false;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getVideoAnswer() {
        return videoAnswer;
    }

    public void setVideoAnswer(String videoAnswer) {
        this.videoAnswer = videoAnswer;
    }

    @Override
    public String toString() {
        return getQuestion();
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }
}
