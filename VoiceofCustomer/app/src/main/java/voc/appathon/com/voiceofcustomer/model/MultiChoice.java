package voc.appathon.com.voiceofcustomer.model;
import java.util.Map;
/**
 * Created by yshetty on 1/16/17.
 */

public class MultiChoice {

    public String choiceType;
    public String questionName;
    public Map<String,String>choices;

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Map<String, String> getChoices() {
        return choices;
    }

    public void setChoices(Map<String, String> choices) {
        this.choices = choices;
    }


}
