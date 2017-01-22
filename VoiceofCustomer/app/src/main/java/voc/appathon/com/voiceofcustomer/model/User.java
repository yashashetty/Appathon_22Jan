package voc.appathon.com.voiceofcustomer.model;

import java.util.Map;

/**
 * Created by yshetty on 1/16/17.
 */

public class User {

    public String userId;
    public boolean isAdmin;
    public Map<String ,Survey>surveys;
    public Map<String,Survey>surveyTaken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Map<String, Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(Map<String, Survey> surveys) {
        this.surveys = surveys;
    }

    public Map<String, Survey> getSurveyTaken() {
        return surveyTaken;
    }

    public void setSurveyTaken(Map<String, Survey> surveyTaken) {
        this.surveyTaken = surveyTaken;
    }




}
