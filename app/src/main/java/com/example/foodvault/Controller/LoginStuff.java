package com.example.foodvault.Controller;

import com.example.foodvault.Model.UserCRUD;

public class LoginStuff {
    UserCRUD userCRUD;
    public LoginStuff(String dataPath){
        userCRUD = new UserCRUD(dataPath);
    }
    public boolean loginSuccess(String username, String password){
        return userCRUD.retrieveUserDetails(username, password) != null;
    }
}
