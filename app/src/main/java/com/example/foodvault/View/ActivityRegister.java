package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodvault.Model.UserCRUD;
import com.example.foodvault.Model.UserDetails;
import com.example.foodvault.R;

import java.io.IOException;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {

    private EditText firstName, lastName, username, email, password;
    UserCRUD userCRUD;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        enableFullscreen();
        userCRUD = new UserCRUD(this);
        firstName = findViewById(R.id.editTextFName);
        lastName = findViewById(R.id.editTextLName);
        username = findViewById(R.id.editTextusername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPass);
        createButton = findViewById(R.id.btnRegister);
        createButton.setOnClickListener(this);
    }

    private void enableFullscreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnRegister) {
            if(firstName.getText().toString().equals("")
            || lastName.getText().toString().equals("")
            || username.getText().toString().equals("")
            || email.getText().toString().equals("")
            || password.getText().toString().equals(""))
            {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_LONG).show();
            } else {
                userCRUD.createUserDetails(new UserDetails(firstName.getText().toString(),
                        lastName.getText().toString(), username.getText().toString(),
                        email.getText().toString(), password.getText().toString()));
                try {
                    userCRUD.saveToFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, ActivityLogin.class));
            }
        }
    }
}