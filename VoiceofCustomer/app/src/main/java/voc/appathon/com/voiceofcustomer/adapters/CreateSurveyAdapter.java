package voc.appathon.com.voiceofcustomer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.model.Survey;
import voc.appathon.com.voiceofcustomer.utils.Constants;

/**
 * Created by yshetty on 1/21/17.
 */

public class CreateSurveyAdapter extends RecyclerView.Adapter<CreateSurveyAdapter.MyViewHolder> {
    private Context mContext;
    private List<Survey> surveys;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question;
        public RadioButton choice_1;
        public RadioButton choice_2;
        public RadioButton choice_3;
        public RadioButton choice_4;
        public Spinner choice_spinner;
        public EditText et_commentbox;
        public LinearLayout ll_rb_choice;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.tv_question);
            choice_1 = (RadioButton) view.findViewById(R.id.rb_choice1);
            choice_2 = (RadioButton) view.findViewById(R.id.rb_choice2);
            choice_3 = (RadioButton) view.findViewById(R.id.rb_choice3);
            choice_4 = (RadioButton) view.findViewById(R.id.rb_choice4);
            choice_spinner = (Spinner) view.findViewById(R.id.spinner_choice);
            et_commentbox = (EditText) view.findViewById(R.id.edit_comment_box);
            ll_rb_choice =(LinearLayout)view.findViewById(R.id.ll_rb_choice);
        }
    }


    public CreateSurveyAdapter(Context mContext, List<Survey> survey) {
        this.mContext = mContext;
        this.surveys = survey;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Survey survey = surveys.get(position);

        if(survey.getQuestions().getType().toString().equals(Constants.CREATE_SURVEY_MULTICHOICE)){
            holder.question.setText(survey.questions.getMultichoice().getQuestionName().toString());
            if(survey.getQuestions().getMultichoice().getChoiceType().equals(Constants.CREATE_SURVEY_RADIO_GROUP)){
                holder.ll_rb_choice.setVisibility(View.VISIBLE);
                holder.et_commentbox.setVisibility(View.GONE);
                holder.choice_spinner.setVisibility(View.GONE);

               ArrayList<String>choices = getChoices(survey);
                populatechoice(choices,holder);



            }else{
                holder.ll_rb_choice.setVisibility(View.GONE);

                holder.et_commentbox.setVisibility(View.GONE);
                holder.choice_spinner.setVisibility(View.VISIBLE);
            }

        }else if(survey.getQuestions().getType().toString().equals(Constants.CREATE_SURVEY_RATING)){
            holder.ll_rb_choice.setVisibility(View.GONE);
            holder.et_commentbox.setVisibility(View.GONE);
            holder.choice_spinner.setVisibility(View.GONE);

        }else if(survey.getQuestions().getType().toString().equals(Constants.CREATE_SURVEY_COMMENTBOX)){
            holder.question.setText(survey.questions.getMultichoice().getQuestionName().toString());
            holder.ll_rb_choice.setVisibility(View.GONE);
            holder.et_commentbox.setVisibility(View.VISIBLE);
            holder.choice_spinner.setVisibility(View.GONE);
        }


        //holder.count.setText(album.getNumOfSongs() + " songs");

        // loading album cover using Glide library
       /* Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

        holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    /*private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_album, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }*/

    /**
     * Click listener for popup menu items
     */
    /*class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }*/

    @Override
    public int getItemCount() {
        return surveys.size();
    }

public ArrayList<String> getChoices(Survey survey){
    Map<String,String>choices = survey.getQuestions().getMultichoice().getChoices();
    ArrayList<String>choice = new ArrayList<>();
    for (Map.Entry<String, String> entry : choices.entrySet())
    {
        choice.add(entry.getValue());
        System.out.println(entry.getKey() + "/" + entry.getValue());
    }
  return  choice;
}

    public void populatechoice(ArrayList<String> choice ,MyViewHolder holder){


       for(int i=0; i<choice.size(); i++){

           if(i==0){
               holder.choice_1.setText(choice.get(0));
               holder.choice_1.setVisibility(View.VISIBLE);
           }else if(i==1){
               holder.choice_2.setText(choice.get(1));
               holder.choice_2.setVisibility(View.VISIBLE);
           }else if(i==2){
               holder.choice_3.setText(choice.get(2));
               holder.choice_3.setVisibility(View.VISIBLE);
           }else if(i==3){
               holder.choice_4.setText(choice.get(3));
               holder.choice_4.setVisibility(View.VISIBLE);
           }

       }

    }

}
