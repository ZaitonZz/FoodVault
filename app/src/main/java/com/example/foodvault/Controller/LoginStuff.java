package com.example.foodvault.Controller;

import com.example.foodvault.Model.UserCRUD;

public class LoginStuff {
    UserCRUD userCRUD;
    public LoginStuff(){
        userCRUD = new UserCRUD();
    }
    public boolean loginSuccess(String username, String password){
        return userCRUD.retrieveUserDetails(username, password) != null;
    }
}
