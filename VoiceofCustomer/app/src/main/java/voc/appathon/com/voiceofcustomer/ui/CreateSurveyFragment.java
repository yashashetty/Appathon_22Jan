package voc.appathon.com.voiceofcustomer.ui;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.adapters.CreateSurveyAdapter;
import voc.appathon.com.voiceofcustomer.firebase.FirebaseService;
import voc.appathon.com.voiceofcustomer.model.MultiChoice;
import voc.appathon.com.voiceofcustomer.model.Questions;
import voc.appathon.com.voiceofcustomer.model.Survey;
import voc.appathon.com.voiceofcustomer.utils.Constants;

/**
 * Created by yshetty on 1/15/17.
 */

public class CreateSurveyFragment extends Fragment{
    private CardView mCardView;
    FirebaseService firebaseService;
    private RecyclerView recyclerView;
    CreateSurveyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_survey_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // mCardView = (CardView) view.findViewById(R.id.cardview);

        firebaseService = FirebaseService.getInstance();
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);

       // albumList = new ArrayList<>();
        Survey sur =AddSurvey();
        ArrayList<Survey>surveys = new ArrayList<>();
        surveys.add(sur);
        adapter = new CreateSurveyAdapter(getContext(), surveys);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);







       // mDatabase.child("users").child(mUserId).child("items").push().child("title").setValue("test do");

    }


    public  Survey AddSurvey(){
        Survey survey = new Survey ();
        survey.setSurveyTitle("Customer Satisfaction Survey");


        Questions questions = new Questions();
        questions.setType(Constants.CREATE_SURVEY_MULTICHOICE);
        MultiChoice multichoiceQuestion = new MultiChoice();
        multichoiceQuestion.setChoiceType(Constants.CREATE_SURVEY_RADIO_GROUP);
        multichoiceQuestion.setQuestionName("How satisfied are you with your last metro ride ");
        Map<String,String> choices = new HashMap<>();
        choices.put("Choice1","Very much Satisfied");
        choices.put("Choice2","Not Satisfied");
        multichoiceQuestion.setChoices(choices);
        questions.setMultichoice(multichoiceQuestion);
        survey.setQuestions(questions);


        return survey;
       //String key = firebaseService.CreateSurvey(survey);
        //Log.d("Key is",key);
        /*mDatabase.child("VoiceOfCustomer").child("Survey").push().setValue(survey);
        mDatabase.goOffline();*/


    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
