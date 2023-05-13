package com.example.foodvault.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserCRUD {
    ArrayList<UserDetails> userList = new ArrayList<>();
    public UserCRUD(String path){
        File file = new File(path + "/userDetails.txt");
        try {
            loadFromFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createUserDetails(UserDetails ud) {
        userList.add(ud);
    }

    public UserDetails retrieveUserDetails(String username, String password) {
        for (UserDetails ud : userList) {
            if (ud.getUsername().equals(username) && ud.getPassword().equals(password)) {
                return ud;
            }
        }
        return null;
    }

    public void updateTeacherSubject(UserDetails ud) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(ud.getUsername()) &&
                    userList.get(i).getPassword().equals(ud.getPassword())) {
                userList.set(i, ud);
                break;
            }
        }
    }

    public void deleteTeacherSubject(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username) &&
                    userList.get(i).getPassword().equals(password)) {
                userList.remove(i);
                break;
            }
        }
    }
    public void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("userDetails.txt"));
        for (UserDetails ud : userList) {
            writer.write(ud.getFirstName() + "," + ud.getLastName() + "," + ud.getUsername() + "," + ud.getEmail() + "," + ud.getPassword() + "\n");
        }
        writer.close();
    }

    public void loadFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("userDetails.txt"));
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
