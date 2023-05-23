package com.example.foodvault.Model;


import java.util.ArrayList;

public class UserCRUD {
    private ArrayList<UserDetails> userList;

    public UserCRUD() {
        userList = new ArrayList<>();
    }

    public void createUserDetails(UserDetails ud) {
        if (retrieveUserByUsername(ud.getUsername()) == null) {
            userList.add(ud);
        }
    }

    public UserDetails retrieveUserByUsername(String uname) {
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

    public void updateUserDetails(UserDetails ud) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(ud.getUsername()) &&
                    userList.get(i).getPassword().equals(ud.getPassword())) {
                userList.set(i, ud);
                break;
            }
        }
    }

    public void deleteUserDetails(String username, String password) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(username) &&
                    userList.get(i).getPassword().equals(password)) {
                userList.remove(i);
                break;
            }
        }
    }

}
