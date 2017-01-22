package voc.appathon.com.voiceofcustomer.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.ui.LogInActivity;


/**
 * Full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreen extends AppCompatActivity implements Handler.Callback {
    //Handler object for running the splashscreen image thread for n number of milli Secs
    private Handler mHandler = null;
    private boolean isRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        initViews();
    }


    protected void initViews() {
        addSplashScreenFragment();
        mHandler = new Handler(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        int DELAY = 4000;
        isRunning = true;
        mHandler.postDelayed(runnableTask, DELAY);
    }

    /**
     * Handler thread which starts Login or dashboard
     */
    private final Runnable runnableTask = new Runnable() {
        @Override
        public void run() {

                showLoginActivity();
                finish();
        }


    };




    private final void showLoginActivity() {
        Intent intent = new Intent(SplashScreen.this, LogInActivity.class);
        //overridePendingTransition(R.anim.card_flip_left_in, R.anim.card_flip_right_out);
        startActivity(intent);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isRunning) {
            isRunning = !isRunning;
            mHandler.removeCallbacks(runnableTask);
        }
    }


    private void addSplashScreenFragment() {
        FragmentTransaction ftrans = getFragmentManager().beginTransaction();
        ftrans.replace(R.id.container, new SplashScreenFragment(), SplashScreenFragment.class.getSimpleName().toString());
       /* ftrans.setCustomAnimations(

                R.anim.card_flip_right_in,
                R.anim.card_flip_right_out,
                R.anim.card_flip_left_in,
                R.anim.card_flip_left_out);*/
        ftrans.commit();
    }

    /*
     * This is written as fragments so as to recreate the SplashScreen in landscape with appropirate background
     */
    public static class SplashScreenFragment extends Fragment {
        public SplashScreenFragment() {

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Hold the screen for a while based on the condition of App token & GCM registration ID.
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final RelativeLayout splashScreenView = (RelativeLayout) inflater.inflate(R.layout.fragmnt_splashscreen, container, false);
            // AnimatorSet animations = new AnimatorSet();
            Animation topAnimtn = AnimationUtils.loadAnimation(getActivity(), R.anim.top_to_bottom);
            Animation leftAnimtn = AnimationUtils.loadAnimation(getActivity(), R.anim.left_to_right);
            final Animation zoomAnimtn = AnimationUtils.loadAnimation(getActivity(), R.anim.zoom_out);
            TextView customer = (TextView) splashScreenView.findViewById(R.id.customer);
            TextView success = (TextView) splashScreenView.findViewById(R.id.success);
            final ImageView scImg = (ImageView) splashScreenView.findViewById(R.id.schneider);

            Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((ImageView) splashScreenView.findViewById(R.id.splash_img)).setVisibility(View.VISIBLE);
                    // ((ImageView) splashScreenView.findViewById(R.id.schneider)).setVisibility(View.VISIBLE);
                    scImg.setAnimation(zoomAnimtn);
                }
            }, 1200);


            customer.setAnimation(leftAnimtn);

            success.setAnimation(topAnimtn);
            //scImg.setVisibility(View.VISIBLE);

            return splashScreenView;


        }

        @Override
        public void onConfigurationChanged(Configuration newConfig) {
            super.onConfigurationChanged(newConfig);
            //This ensures to remove all views in landscape or protrait with new orientation layout
            ViewGroup viewGroup = (ViewGroup) getView();
            viewGroup.removeAllViewsInLayout();
            View view = onCreateView(getActivity().getLayoutInflater(), viewGroup, null);
            viewGroup.addView(view);
        }
    }


}
