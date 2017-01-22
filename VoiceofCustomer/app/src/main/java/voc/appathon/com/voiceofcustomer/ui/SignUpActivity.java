package voc.appathon.com.voiceofcustomer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import voc.appathon.com.voiceofcustomer.R;

/**
 * Created by yshetty on 01/15/17.
 */
public class SignUpActivity extends AppCompatActivity {

    protected EditText passwordEditText;
    protected EditText emailEditText;
    protected ImageView signUpButton;
    protected TextView loginbtn;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mFirebaseAuth = FirebaseAuth.getInstance();

        passwordEditText = (EditText)findViewById(R.id.input_password);
        emailEditText = (EditText)findViewById(R.id.input_email);
        signUpButton = (ImageView) findViewById(R.id.btn_signup);
        loginbtn=(TextView)findViewById(R.id.link_login) ;

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
              finish();
         }
        });


    }


    public  void signUp(){
        String password = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();

        password = password.trim();
        email = email.trim();

        if (password.isEmpty() || email.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
            builder.setMessage(R.string.signup_error_message)
                    .setTitle(R.string.signup_error_title)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            mFirebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //mFirebaseAuth.getCurrentUser().getProviders().
                                Intent intent = new Intent(SignUpActivity.this, DashBoardScreen.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage(task.getException().getMessage())
                                        .setTitle(R.string.login_error_title)
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }
                    });
        }
    }

}
