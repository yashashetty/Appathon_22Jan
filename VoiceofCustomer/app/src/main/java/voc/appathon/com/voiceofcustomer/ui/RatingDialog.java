package voc.appathon.com.voiceofcustomer.ui;


import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import voc.appathon.com.voiceofcustomer.R;


/**
 * Dialog fragment shown with app theme. on user account clicked
 */
public class RatingDialog extends DialogFragment implements View.OnClickListener {
    private ImageView satisfactionExp;

    private Button submit;
    private SeekBar seekBar;
    private TextView message;
    private int currentProgress = 65;

    private int vBad = 20;
    private int bad = 40;
    private int ok = 60;
    private int good = 80;
    private int vGood = 100;

    private Dialog dialog;
    private String userComments;
    private String slideCount = "4";
    EditText commentsText;


    public RatingDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.rating_dialog);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Dialog customization
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
        getDialog().setCancelable(true);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        getDialog().getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        //End of customizing dailog theme

        LinearLayout containerView = (LinearLayout) inflater.inflate(R.layout.activity_rating, container, false);
        initViews(containerView);
        containerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RatingDialog.this.dismiss();
            }
        });
        return containerView;
    }

    private void initViews(ViewGroup container) {
        satisfactionExp = (ImageView) container.findViewById(R.id.satisfaction_exp);
        submit = (Button) container.findViewById(R.id.submit_survey);
        submit.setOnClickListener(this);
        seekBar = (SeekBar) container.findViewById(R.id.seekBar);
        message = (TextView) container.findViewById(R.id.satisfaction_message);
        seekBar.getProgressDrawable().setColorFilter(
                Color.parseColor("#1EA351"),
                android.graphics.PorterDuff.Mode.SRC_IN);

        Drawable thumb = getResources().getDrawable(R.drawable.seekthumb);
        seekBar.setThumb(thumb);
        seekBar.setProgress(currentProgress);
        seekBar.setOnSeekBarChangeListener(seekChangeListener);

    }

    SeekBar.OnSeekBarChangeListener seekChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
            currentProgress = progress;
            setProgressData(progress);

        }

        @Override
        public void onStartTrackingTouch(SeekBar arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStopTrackingTouch(SeekBar arg0) {
            // TODO Auto-generated method stub

        }

    };

    // Set data based on seekbar progress.
    private void setProgressData(int progress) {

        if (progress >= 0 && progress <= vBad) {
            slideCount = "1";
            satisfactionExp.setImageResource(R.drawable.sad_1);
            message.setVisibility(View.VISIBLE);

        } else if (progress > vBad && progress <= bad) {
            slideCount = "2";
            satisfactionExp.setImageResource(R.drawable.sad_2);

            message.setVisibility(View.INVISIBLE);
        } else if (progress > bad && progress <= ok) {
            slideCount = "3";
            satisfactionExp.setImageResource(R.drawable.ok);

            message.setVisibility(View.INVISIBLE);
        } else if (progress > ok && progress <= good) {
            slideCount = "4";
            satisfactionExp.setImageResource(R.drawable.happy_1);
            message.setVisibility(View.VISIBLE);

        } else if (progress > good && progress <= vGood) {
            slideCount = "5";
            satisfactionExp.setImageResource(R.drawable.happy_2);
            message.setVisibility(View.VISIBLE);

        }

    }


    //SingleTon instance
    public static RatingDialog getInstance() {
        RatingDialog dialog = new RatingDialog();
        //put any bundle if necessary
        return dialog;
    }

    public void show(FragmentManager manager, String tag) {
        if (manager.findFragmentByTag(tag) == null) {
            super.show(manager, tag);
        }
    }

    @Override
    public void onClick(View view) {



        if (slideCount.equals("1")) {
            //vbad
        } else if (slideCount.equals("2")) {
            //bad

        } else if (slideCount.equals("3")) {
            //ok


        } else if (slideCount.equals("4")) {
            //good
        } else if (slideCount.equals("5")) {
            //vgood
        }
        Toast.makeText(getActivity(),"Your ratings submitted sucessfully",Toast.LENGTH_LONG).show();
        dismiss();
    }
}
