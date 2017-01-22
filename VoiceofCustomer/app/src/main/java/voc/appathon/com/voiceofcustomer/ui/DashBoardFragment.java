package voc.appathon.com.voiceofcustomer.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import voc.appathon.com.voiceofcustomer.R;

/**
 * Created by yshetty on 1/21/17.
 */

public class DashBoardFragment extends Fragment {
    private Button btn_createSurvey;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_dashboard, container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn_createSurvey = (Button)view.findViewById(R.id.create_survey);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* DashBoardFragment fragment = new DashBoardFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment).commit();*/





        btn_createSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
