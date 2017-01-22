package voc.appathon.com.voiceofcustomer.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import voc.appathon.com.voiceofcustomer.R;
import voc.appathon.com.voiceofcustomer.adapters.CreateSurveyAdapter;
import voc.appathon.com.voiceofcustomer.firebase.FirebaseService;
import voc.appathon.com.voiceofcustomer.model.MultiChoice;
import voc.appathon.com.voiceofcustomer.model.Questions;
import voc.appathon.com.voiceofcustomer.model.Survey;
import voc.appathon.com.voiceofcustomer.utils.Constants;

public class SurveyCreationActivity extends BaseAcitivity{

    private static final int SELECT_PICTURE = 0;
    private static final int REQUEST_CAMERA = 1;
    private ImageView mImageView;
    private TextView tvTitle;
    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    private CardView mCardView;
    FirebaseService firebaseService;
    private RecyclerView recyclerView;
    private String survey_key;
    CreateSurveyAdapter adapter;
    FloatingActionButton floatingActionMultipleChoice;
FirebaseService service;
    ArrayList<Survey> surveys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.create_survey_fragment);
        service = FirebaseService.getInstance();
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //initViews();
        firebaseService = FirebaseService.getInstance();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
         tvTitle =(TextView)findViewById(R.id.survey_tittle) ;
        floatingActionMultipleChoice = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        // albumList = new ArrayList<>();
        Survey sur =AddSurvey();
        surveys = new ArrayList<>();
        surveys.add(sur);
        adapter = new CreateSurveyAdapter(this, surveys);
        if(getIntent().getStringExtra(Constants.CREATE_SURVEY_TITTLE) != null){
            tvTitle.setText(getIntent().getStringExtra(Constants.CREATE_SURVEY_TITTLE).toString());
        }

        if(getIntent().getStringExtra(Constants.SURVEY_KEY) != null){
           survey_key =getIntent().getStringExtra(Constants.SURVEY_KEY).toString();
        }
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new SurveyCreationActivity.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        floatingActionMultipleChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(SurveyCreationActivity.this,MultipleChoiceQuestionScreen.class);
                intent.putExtra(Constants.SURVEY_KEY,survey_key);
                startActivity(intent);
            }
        });

//comment
        Query query =service.getSurvey(survey_key);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // surveys = new ArrayList<Survey>();

                    Survey survey1 = dataSnapshot.getValue(Survey.class);
                surveys.add(survey1);

adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*private void initViews() {
        Button mpickPhoto = (Button) findViewById(R.id.photo);
        mpickPhoto.setOnClickListener(SurveyCreationActivity.this);

        Button calender = (Button) findViewById(R.id.calender);
        mpickPhoto.setOnClickListener(this);
    }*/

   /* @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.photo:
                pickImage();
                break;
            case R.id.calender:
                pickDate();
                break;
        }
    }*/



    private void pickDate() {



    }

   /* private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
           /* et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);*/
       // }
   //};*/

   /* private void pickImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_PICTURE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }*/

  /*  @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bm;
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                    btmapOptions.inSampleSize = 2;
                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);

                    // bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
                    mImageView.setImageBitmap(bm);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "test";
                    f.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");
                    fOut = new FileOutputStream(file);
                    bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                String tempPath = getPath(selectedImageUri, this);
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                btmapOptions.inSampleSize = 2;

                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
               // mImageView = (ImageView) findViewById(R.id.survey_image);
               // mImageView.setImageBitmap(bm);
            }
        }
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);


    }*/





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
