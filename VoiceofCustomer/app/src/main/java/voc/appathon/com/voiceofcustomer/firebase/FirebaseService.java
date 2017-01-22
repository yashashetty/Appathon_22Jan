package voc.appathon.com.voiceofcustomer.firebase;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;

import voc.appathon.com.voiceofcustomer.model.Survey;
import voc.appathon.com.voiceofcustomer.model.UserSurvey;
import voc.appathon.com.voiceofcustomer.ui.DashBoardScreen;

/**
 * Created by yshetty on 1/17/17.
 */

public class FirebaseService {

    private static FirebaseService instance;

    private static FirebaseAuth mFirebaseAuth;
    private static FirebaseUser mFirebaseUser;
    private static DatabaseReference mDatabase;
    private static final String NODE_ROOT = "VoiceOfCustomer";
    private static final String NODE_SURVEY ="Survey";
    private static final String NODE_USERS ="Users";
    private static final String NODE_USERS_SURVEYS ="Surveys";
    private static final String NODE_SURVEY_TOTALRESPONSE ="totalResponse";
    private static final String NODE_SURVEY_LIKES ="likes";
    Survey survey ;
    public static synchronized FirebaseService getInstance() {
        if (instance == null) {
            instance = new FirebaseService();
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser = mFirebaseAuth.getCurrentUser();
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }
        return instance;
    }

    public String CreateSurvey(Survey survey){

        DatabaseReference data =  mDatabase.child(NODE_ROOT).child(NODE_SURVEY);
        DatabaseReference create_data = data.push();
        survey.setSurveyID(create_data.getKey());
        create_data.setValue(survey);

        updateUserInfo(survey,create_data.getKey());

        return create_data.getKey();

    }

    public void updatSurvey(Survey survey,String key){

        DatabaseReference data =  mDatabase.child(NODE_ROOT).child(NODE_SURVEY).child(key);

        data.setValue(survey);


    }

   public void updateUserInfo(Survey survey,String key){

       DatabaseReference data =  mDatabase.child(NODE_ROOT).child(NODE_USERS).child(DashBoardScreen.mUserId).child(NODE_USERS_SURVEYS);
       DatabaseReference create_data = data.push();
       UserSurvey userSurvey= new UserSurvey();
       //userSurvey.setSurveyID(key);
       create_data.setValue(key);

   }

    public Query getSurveyOfUserid(String userID){

        Query surveyIDQuery = mDatabase.child(NODE_ROOT).child(NODE_USERS).child(userID).child(NODE_USERS_SURVEYS);


        return surveyIDQuery;


    }


    public Query getSurvey() {

        Query query = mDatabase.child(NODE_ROOT).child(NODE_SURVEY);

         return query;

        //GenericTypeIndicator<List<Survey>> typeIndicator = new GenericTypeIndicator<List<Survey>>() {};
        //List<Beer> beers = snapshot.getValue(typeIndicator);

    }


    public Query getSurvey(String survey_key) {

        Query query = mDatabase.child(NODE_ROOT).child(NODE_SURVEY).child(survey_key);

        return query;

        //GenericTypeIndicator<List<Survey>> typeIndicator = new GenericTypeIndicator<List<Survey>>() {};
        //List<Beer> beers = snapshot.getValue(typeIndicator);

    }


    public void updateResponse(String surveyid){
        DatabaseReference data =  mDatabase.child(NODE_ROOT).child(NODE_SURVEY).child(surveyid).child(NODE_SURVEY_TOTALRESPONSE);
        data.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {

                if(mutableData.getValue() != null){
                    mutableData.setValue(Integer.parseInt(mutableData.getValue().toString())+1);
                }else{
                    mutableData.setValue(1);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("Trans complete",dataSnapshot.getValue().toString());
            }
        });

    }

    public void updateLikes(String surveyid){
        DatabaseReference data =  mDatabase.child(NODE_ROOT).child(NODE_SURVEY).child(surveyid).child(NODE_SURVEY_LIKES);
        data.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {

                if(mutableData.getValue() != null){
                    mutableData.setValue(Integer.parseInt(mutableData.getValue().toString())+1);
                }else{
                    mutableData.setValue(1);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                Log.d("Trans complete",dataSnapshot.getValue().toString());
            }
        });

    }

}
