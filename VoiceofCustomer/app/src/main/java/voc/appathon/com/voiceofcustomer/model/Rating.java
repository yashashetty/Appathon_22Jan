package voc.appathon.com.voiceofcustomer.model;

/**
 * Created by yshetty on 1/16/17.
 */
import java.util.Map;
public class Rating {

    public String question;
    public Map<String,String>Rating;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Map<String, String> getRating() {
        return Rating;
    }

    public void setRating(Map<String, String> rating) {
        Rating = rating;
    }



}


