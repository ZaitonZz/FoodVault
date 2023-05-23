package com.example.foodvault.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

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
import com.example.foodvault.Controller.Controller;
import com.example.foodvault.R;

import java.io.IOException;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private Button checkLogin, createAcc;
    private EditText usern, pass;
//    private LoginStuff loginStuff;

    public static String currentUserLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        setContentView(R.layout.activity_login);

        // retrieve recipe data base
        try {
            Controller.loadFromFile(this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
//            loginStuff = new LoginStuff(this);
            if(Controller.UserData.retrieveUserDetails(usern.getText().toString(),pass.getText().toString()) != null){
                currentUserLogIn = usern.getText().toString();
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