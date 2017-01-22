package voc.appathon.com.voiceofcustomer.model;

import java.util.Date;
/**
 * Created by yshetty on 1/16/17.
 */
public class Survey {

    public String surveyID;
    public String surveyName;
    public String surveyTitle;
    public Questions questions;
    public String totalResponse;
    public String likes;
    public String userID;
    public String user_name ;
    public String user_choice;
    public Date createdDate;
    public Date modifiedDate;
    public String category;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyTitle() {
        return surveyTitle;
    }

    public void setSurveyTitle(String surveyTitle) {
        this.surveyTitle = surveyTitle;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public String getTotalResponse() {
        return totalResponse;
    }

    public void setTotalResponse(String totalResponse) {
        this.totalResponse = totalResponse;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_choice() {
        return user_choice;
    }

    public void setUser_choice(String user_choice) {
        this.user_choice = user_choice;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String user_id) {
        this.userID = user_id;
    }


}
