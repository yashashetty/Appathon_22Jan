package voc.appathon.com.voiceofcustomer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.firebase.FirebaseService;
import voc.appathon.com.voiceofcustomer.model.Survey;

/**
 * Created by yshetty on 1/17/17.
 */

public class ViewSurveyFragment extends Fragment{
    private FirebaseService firebaseService;
    private ArrayList<Survey>surveys;
    private TextView textview1 ,textView2,textview3;

   ArrayList<String> surveyID = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textview1 = (TextView) view.findViewById(R.id.textView);
        textView2 = (TextView)view.findViewById(R.id.textView2);
        textview3 =(TextView)view.findViewById(R.id.textView3);

        firebaseService = FirebaseService.getInstance();
        Query surveyIDOfUserid= firebaseService.getSurveyOfUserid(DashBoardScreen.mUserId);
        surveyIDOfUserid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){

                    surveyID.add(data.getValue().toString());
                }

                populateSurvey();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });

    }

    public void populateSurvey(){
        Query query =  firebaseService.getSurvey();
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               surveys = new ArrayList<Survey>();
                for(DataSnapshot data: dataSnapshot.getChildren()){

                    for(int i=0;i< surveyID.size();i++){
                        if(data.getKey().equals(surveyID.get(i))){
                            Survey survey1 = data.getValue(Survey.class);
                            surveys.add(survey1);
                            Log.d("tanu","sur--------------"+survey1.getQuestions().toString());
                        }
                    }
                }

                loadView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }


    public  void loadView(){

        Log.d("Size of survey is ",Integer.toString(surveys.size()));

        Survey survey = surveys.get(0);
        Log.d("tanu","title-------"+survey.getSurveyTitle().toString());
        Log.d("tanu","cat-------"+survey.getCategory());
        Log.d("tanu","nm-------"+survey.getSurveyName());
        Log.d("tanu","title-------"+survey.getTotalResponse());
        Log.d("tanu","title-------"+survey.getQuestions());
        Log.d("tanu","title-------"+survey.getModifiedDate());
        textview1.setText(survey.getSurveyTitle().toString());
        Survey survey1 = surveys.get(1);
        textView2.setText(survey1.getSurveyTitle());
        Survey survey2 = surveys.get(2);
        textview3.setText(survey2.getSurveyTitle());

    }
}
