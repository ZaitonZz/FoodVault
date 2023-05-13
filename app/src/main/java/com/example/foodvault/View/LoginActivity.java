package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodvault.Controller.LoginStuff;
import com.example.foodvault.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_STORAGE = 1000;
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
//        String dataDir = this.getApplicationInfo().dataDir;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }
//        enableFullscreen();
    }
//    private void enableFullscreen() {
//        View decorView = getWindow().getDecorView();
//        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                        View.SYSTEM_UI_FLAG_FULLSCREEN |
//                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        );
//    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.loginButton) {
            LoginStuff placeholder = new LoginStuff(this);
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