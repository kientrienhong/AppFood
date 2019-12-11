package com.hkt.summery;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        email = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
    }

    public boolean isEmpty(EditText editText){
        return editText.getText().toString().isEmpty();
    }


    public void signUp (View view) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    public boolean checkAllEditView () {
        int count = 0;
        if (isEmpty(email)){
            email.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            email.setError("Please enter your email");
            count++;
        }

        if(isEmpty(password)){
            password.getBackground().mutate().setColorFilter(getResources().getColor(android.R.color.holo_red_light), PorterDuff.Mode.SRC_ATOP);
            password.setError("Please enter your passwprd");
            count++;
        }

        if (count == 0){
            return true;
        } else {
            return false;
        }
    }

    public void signIn(View view) {
        if (checkAllEditView()){
            Intent intent1 = new Intent(MainActivity.this, SuccessfulLogin.class);
            startActivity(intent1);
        }
    }
}
