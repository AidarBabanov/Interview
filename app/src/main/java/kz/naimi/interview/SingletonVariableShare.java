package kz.naimi.interview;

import java.util.List;

public class SingletonVariableShare {
    private static SingletonVariableShare instance;

    private List<InterviewElement> interview;

    public static SingletonVariableShare getInstance() {
        if(instance==null)instance = new SingletonVariableShare();
        return instance;
    }

    public List<InterviewElement> getInterview() {
        return interview;
    }

    public void setInterview(List<InterviewElement> interview) {
        this.interview = interview;
    }
}
