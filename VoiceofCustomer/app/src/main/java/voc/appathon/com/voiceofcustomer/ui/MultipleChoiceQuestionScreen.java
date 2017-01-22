package voc.appathon.com.voiceofcustomer.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.firebase.FirebaseService;
import voc.appathon.com.voiceofcustomer.model.MultiChoice;
import voc.appathon.com.voiceofcustomer.model.Questions;
import voc.appathon.com.voiceofcustomer.model.Survey;
import voc.appathon.com.voiceofcustomer.utils.Constants;
/**
 * Created by yshetty on 1/22/17.
 */

public class MultipleChoiceQuestionScreen extends Activity {

    EditText et_question;
    EditText choice_1;
    EditText choice_2;
    EditText choice_3;
    EditText choice_4;

    Button btn_save;
    String survey_key;
    FirebaseService fbservice;
    Survey survey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.multiple_choice_layout);
        fbservice = FirebaseService.getInstance();
        et_question = (EditText)findViewById(R.id.et_question);
        choice_1 = (EditText)findViewById(R.id.et_choice1);
        choice_2 = (EditText)findViewById(R.id.et_choice2);
        choice_3 = (EditText)findViewById(R.id.et_choice3);
        choice_4 = (EditText)findViewById(R.id.et_choice4);
        btn_save = (Button) findViewById(R.id.btn_save);


        if(getIntent().getStringExtra(Constants.SURVEY_KEY)!= null){
            survey_key =getIntent().getStringExtra(Constants.SURVEY_KEY).toString();
        }

        Query query_survey = fbservice.getSurvey(survey_key);


        query_survey.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                survey = dataSnapshot.getValue(Survey.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               validateData();
                survey =updteSurveyObj(survey);

                fbservice.updatSurvey(survey,survey_key);
finish();
            }
        });


    }

    public  void validateData(){

    }


    public  Survey updteSurveyObj(Survey survey){

        Questions question = new Questions();
        question.setType(Constants.CREATE_SURVEY_MULTICHOICE);
        MultiChoice choice = new MultiChoice();
        choice.setQuestionName(et_question.getText().toString());
        Map<String,String> choices = new HashMap<>();
        choices.put("Choice1",choice_1.getText().toString());
        choices.put("Choice2",choice_2.getText().toString());
        choices.put("Choice3",choice_3.getText().toString());
        choices.put("Choice4",choice_4.getText().toString());
         choice.setChoices(choices);
        question.setMultichoice(choice);

        survey.setQuestions(question);

       return  survey;
    }
}
