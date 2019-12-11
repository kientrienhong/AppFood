package com.hkt.summery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private static final Pattern PASS_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" + // at least 1 digit
                    "(?=.*[a-z])" + // at least 1 lower case letter
                    "(?=.*[A-Z])" + // at least 1 upper case letter
                 //   "(?=.*[a-zA-Z])" + // any letter
                    "(?=\\S+$)" +   // no white space
                    ".{6,20}" +     // at least 6 character max 20
                    "$");
    EditText username, password, confirm, email;
    Button signUp;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        intent = getIntent();
        username = (EditText) findViewById(R.id.signUpUsername);
        password = (EditText) findViewById(R.id.password);
        confirm = (EditText) findViewById(R.id.confirmPassword);
        email = (EditText) findViewById(R.id.signUpEmail);
        signUp = (Button) findViewById(R.id.signUp);
    }


    public boolean isEmpty(EditText editText){
        return editText.getText().toString().isEmpty();
    }

    public boolean checkMail(EditText editText){
        String emailInput = editText.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            return false;
        } else{
            return true;
        }
    }

    public boolean checkPassword (EditText editText){
        if (!PASS_PATTERN.matcher(editText.getText().toString()).matches()){
            return false;
        } else{
            return true;
        }
    }

    public void logIn(View view) {
        intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void signUp(View view) {
        if (isEmpty(username)){
            username.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            username.setError("Please enter your username");
        }

        if (isEmpty(email)){
            email.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            email.setError("Please enter your email");
        } else {
            if (!checkMail(email)){
                email.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                email.setError("Email invalid");
            }
        }

        if (isEmpty(confirm)){
            confirm.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            confirm.setError("Please confirm your password");
        } else {
            if (!confirm.getText().toString().equals(password.getText().toString())){
                confirm.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                confirm.setError("Password is not match");
            }
        }

        if (isEmpty(password)){
            password.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            password.setError("Please enter your password");
        } else {
            if (!checkPassword(password)){
                password.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
                password.setError("Invalid password.\n - At least 1 upper character \n - At least 1 digit " +
                        "\n - At least 6 character \n - Maximum 20 characters");
            }
        }

    }
}
