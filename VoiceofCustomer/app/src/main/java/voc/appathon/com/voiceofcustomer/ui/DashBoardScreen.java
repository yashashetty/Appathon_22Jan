package voc.appathon.com.voiceofcustomer.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.firebase.FirebaseService;
import voc.appathon.com.voiceofcustomer.utils.StringUtils;
import voc.appathon.com.voiceofcustomer.model.*;
import voc.appathon.com.voiceofcustomer.utils.*;
//main

public class DashBoardScreen extends BaseAcitivity implements View.OnClickListener {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    public static String mUserId;

    private Bundle bundle;
    private  Button btnCreateSurvey;
    private Button btnViewSurvey;
    private Button btnIncreaseResponse;
    ViewPagerAdapter adapter;
    FirebaseService firebaseservice;
    //ViewPagerAdapter adapter;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.survey);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
firebaseservice = FirebaseService.getInstance();
        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        }else {
            initViews();
            mUserId = mFirebaseUser.getUid();

            mDatabase.child("VoiceOfCustomer").child("Users").child(mUserId).child("isAdmin").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println(dataSnapshot.getValue());
                    if(dataSnapshot.getValue()!= null && Boolean.parseBoolean(dataSnapshot.getValue().toString())){
                        btnCreateSurvey.setVisibility(View.VISIBLE);
                    }else{
                        btnCreateSurvey.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



           // Set up ListView
            //final ListView listView = (ListView) findViewById(R.id.listView);
           // final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
            //listView.setAdapter(adapter);

            // Add items via the Button and EditText at the bottom of the view.

           /* btnCreateSurvey.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mDatabase.getRoot().getDatabase().getReference("VoiceOfCustomer").child("Users").child(mUserId).push().setValue("users");
               // id = mDatabase.child("VoiceOfCustomer").child("Users").child(z).push().child("title").setValue("test do");
                 //   mDatabase.child("VoiceOfCustomer").child("Users").child(mUserId).push().child("title").
                    //text.setText("");
                }
            });*/

           /* mDatabase.child("voiceofcustomer-214e1").child("Users").child(mUserId).child("items").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    adapter.add((String) dataSnapshot.child("title").getValue());
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    adapter.remove((String) dataSnapshot.child("title").getValue());
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });*/


        }

       /* btnCreateSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    *//*getFragmentManager().beginTransaction()
                            .add(R.id.container, CardViewFragment.newInstance())
                            .commit();*//*
               adapter.addFrag(new CreateSurveyFragment(), "Create Survey");
                adapter.notifyDataSetChanged();
               //// adapter.addFrag(new CreateSurveyFragment(), "Create Survey"); //todo
            ////    adapter.notifyDataSetChanged();//todo
            }
        });*/

      /*  btnViewSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*adapter.addFrag(new ViewSurveyFragment(), "View Survey");
                adapter.notifyDataSetChanged();*/
                /*Intent view = new Intent(DashBoardScreen.this, AnalyzeScreen.class);
                startActivity(view);
            }
        });*/

      /*  btnIncreaseResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseService.getInstance().updateResponse("-KacvOAOACaUr0Q4L6br");
            }
        });

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

 @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.create_survey:

                createSurvey();


                break;
            /*case R.id.add_survey:
                break;*/
            case R.id.edit_survey:
                break;


        }

    }

    private void loadLogInView() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



     private void initViews() {
         btnCreateSurvey = (Button) findViewById(R.id.create_survey);
         btnCreateSurvey.setOnClickListener(this);

         //btnViewSurvey = (Button) findViewById(R.id.add_survey);

        // btnViewSurvey.setOnClickListener(this);
         btnIncreaseResponse = (Button) findViewById(R.id.edit_survey);
         btnIncreaseResponse.setOnClickListener(this);
         ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
         setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
         final String[] colors = {"#96CC7A", "#EA705D", "#66BBCC"};

         AHBottomNavigationItem item1 = new AHBottomNavigationItem(getString(R.string.response), R.drawable.circle_icon_old, R.color.color1);
         AHBottomNavigationItem item2 = new AHBottomNavigationItem(getString(R.string.queries), R.drawable.circle_icon_old, R.color.color2);
         AHBottomNavigationItem item3 = new AHBottomNavigationItem(getString(R.string.top), R.drawable.circle_icon_old, R.color.color3);
         AHBottomNavigationItem item4 = new AHBottomNavigationItem(getString(R.string.like), R.drawable.circle_icon_old,R.color.color1);

         AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
         bottomNavigation.addItem(item1);
         bottomNavigation.addItem(item2);
         bottomNavigation.addItem(item3);
         bottomNavigation.addItem(item4);
         bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
         bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
         bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
         //  Enables Reveal effect
         bottomNavigation.setColored(true);
         bottomNavigation.setCurrentItem(0);
         bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
             @Override
             public boolean onTabSelected(int position, boolean wasSelected) {
                 //fragment.updateColor(Color.parseColor(colors[position]));
                 RatingDialog userAccountDialog = RatingDialog.getInstance();
                 //Attach user data in bundle

                 userAccountDialog.show(getSupportFragmentManager(), "user_dialog");
                 return wasSelected;
             }
         });

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        int i=6;//TODO : come from db
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new CompletedSurveyFragment(), StringUtils.getStringfrmRes(R.string.in_progress_Survey, this)+"("+i+")");
        adapter.addFrag(new InProgressFragment(), StringUtils.getStringfrmRes(R.string.completed_survey, this)+"("+i+")");

        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void createSurvey() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(this);
        View mView = layoutInflaterAndroid.inflate(R.layout.create_survey_dialog, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(this);
        alertDialogBuilderUserInput.setView(mView);

        final EditText userInputDialogEditText = (EditText) mView.findViewById(R.id.userInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if(userInputDialogEditText.getText().toString()!= null && !userInputDialogEditText.getText().toString().equals("")){


                            Survey survey = new Survey ();
                            survey.setSurveyTitle(userInputDialogEditText.getText().toString());

                                String key = firebaseservice.CreateSurvey(survey);

        /*mDatabase.child("VoiceOfCustomer").child("Survey").push().setValue(survey);
        mDatabase.goOffline();*/



                            Intent createSuvey = new Intent(DashBoardScreen.this, SurveyCreationActivity.class);
                            createSuvey.putExtra(Constants.CREATE_SURVEY_TITTLE,userInputDialogEditText.getText().toString());
                            createSuvey.putExtra(Constants.SURVEY_KEY,key);
                            startActivity(createSuvey);
                        }else{
                            userInputDialogEditText.setError("Enter Survey tittle");
                        }
                        // ToDo get user input here
                    }
                })

                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();


        //Intent createSuvey = new Intent(this, SurveyCreationActivity.class);
        //startActivity(createSuvey);

    }
}
