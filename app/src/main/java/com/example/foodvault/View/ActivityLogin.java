package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodvault.Controller.LoginStuff;
import com.example.foodvault.Controller.RecipeController;
import com.example.foodvault.Model.RecipeCatalogue;
import com.example.foodvault.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private Button checkLogin, createAcc;
    private EditText usern, pass;
    private LoginStuff loginStuff;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED ||
                        checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
                                PackageManager.PERMISSION_GRANTED)) {
            // Request the permissions if they are not granted
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, PERMISSION_REQUEST_STORAGE);
        }
//        enableFullscreen();
    }
@Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.loginButton) {
            loginStuff = new LoginStuff(this);
            if(loginStuff.loginSuccess(usern.getText().toString(),pass.getText().toString())){
                startActivity(new Intent(this, ActivityHome.class));
            } else {
                Toast.makeText(this,"User Not Found!", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.createAccButton) {
//            Intent intent = new Intent(this,ActivityRegister.class);
//            intent.extra
            startActivity(new Intent(this, ActivityRegister.class));
        }
    }


}