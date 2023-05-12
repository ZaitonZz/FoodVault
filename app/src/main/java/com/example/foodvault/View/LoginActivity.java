package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodvault.Controller.LoginStuff;
import com.example.foodvault.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button checkLogin, createAcc;
    private EditText usern, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkLogin = findViewById(R.id.loginButton);
        createAcc = findViewById(R.id.createAccButton);
        createAcc.setOnClickListener(this);
        checkLogin.setOnClickListener(this);
        usern = findViewById(R.id.UserLogin);
        pass = findViewById(R.id.editTextTextPassword);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.loginButton) {
            LoginStuff placeholder = new LoginStuff();
            if(placeholder.loginSuccess(usern.getText().toString(),pass.getText().toString())){
                startActivity(new Intent(this, HomeActivity.class));
            } else {
                Toast.makeText(this,"User Not Found!", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.createAccButton) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}