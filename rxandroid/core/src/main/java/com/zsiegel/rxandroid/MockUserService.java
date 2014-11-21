package com.zsiegel.rxandroid;

import com.zsiegel.rxandroid.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockUserService {

    public static List<User> getMockData(int size) {
        List<User> users = new ArrayList<User>();
        for (int idx = 0; idx < size; idx++) {
            User user = new User();
            user.id = idx;
            user.lastUpdated = new Date();
            user.status = (idx % 3 == 0) ? "ONLINE" : "OFFLINE";
            users.add(user);
        }
        return users;
    }
}