package com.campro.onboarding.presentation.request;


import java.util.ArrayList;

public class OnboardingResultRequest {


    private ArrayList<String> results;


    public OnboardingResultRequest(ArrayList<String> results) {
        this.results = results;
    }


    public ArrayList<String> getResults() {
        return results;
    }

}
