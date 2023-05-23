package com.example.foodvault.Model;


import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class UserCRUD {
    private Context context;
    private ArrayList<UserDetails> userList;
    static AssetManager assetManager;

    public UserCRUD() {
        userList = new ArrayList<>();
    }

    public UserCRUD(Context context) {
        this.context = context;
        userList = new ArrayList<>();
        assetManager = context.getAssets();
        File file = new File(context.getExternalFilesDir(null),"userDetails.txt");
        if (!file.exists()) {
            try {
                InputStream inputStream = assetManager.open("userDetails.txt");
                loadFromFile(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else{
            try {
                loadFromFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            saveToFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createUserDetails(UserDetails ud) {
        if (retrieveUserWithUsername(ud.getUsername()) == null) {
            userList.add(ud);
        }
    }

    public UserDetails retrieveUserWithUsername(String uname) {
        for (UserDetails ud: userList) {
            if (ud.getUsername().equals(uname)) {
                return ud;
            }
        }

        return null;
    }

    public UserDetails retrieveUserDetails(String username, String password) {
        for (UserDetails ud : userList) {
            if (ud.getUsername().equals(username) && ud.getPassword().equals(password)) {
                return ud;
            }
        }
        return null;
    }

    public ArrayList<UserDetails> getUserList() {
        return userList;
    }

    public void updateUser(UserDetails ud) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(ud.getUsername()) &&
                    userList.get(i).getPassword().equals(ud.getPassword())) {
                userList.set(i, ud);
                break;
            }
        }
    }

    public void deleteUpdate(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username) &&
                    userList.get(i).getPassword().equals(password)) {
                userList.remove(i);
                break;
            }
        }
    }
    public void saveToFile() throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(context.getExternalFilesDir(null), "userDetails.txt"));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        for (UserDetails ud : userList) {
            writer.write(ud.getFirstName() + "," + ud.getLastName() + "," + ud.getUsername() + "," + ud.getEmail() + "," + ud.getPassword() + "\n");
        }
        writer.flush();
        writer.close();
    }

    public void loadFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(context.getExternalFilesDir(null),"userDetails.txt")));
        String line = reader.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            String firstName = parts[0];
            String lastName = parts[1];
            String username = parts[2];
            String email = parts[3];
            String password = parts[4];
            UserDetails ud = new UserDetails(firstName,lastName,username,email,password);
            userList.add(ud);
            line = reader.readLine();
        }
        reader.close();
    }
    public void loadFromFile(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = reader.readLine();
        while (line != null) {
            String[] parts = line.split(",");
            String firstName = parts[0];
            String lastName = parts[1];
            String username = parts[2];
            String email = parts[3];
            String password = parts[4];
            UserDetails ud = new UserDetails(firstName,lastName,username,email,password);
            userList.add(ud);
            line = reader.readLine();
        }
        reader.close();
    }
}
