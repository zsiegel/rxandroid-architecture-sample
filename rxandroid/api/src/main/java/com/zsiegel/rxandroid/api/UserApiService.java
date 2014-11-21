package com.zsiegel.rxandroid.api;

import com.zsiegel.rxandroid.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * @author zsiegel (zac@akta.com)
 */
public class UserApiService {

    public UserApiService() {
        super();
    }

    public Observable<List<User>> get(final long id) {
        return Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {
                if (id < 0) {
                    //Return all the users ex. make a rest call to /users
                    subscriber.onNext(getMockData());
                } else {
                    //Return a single user ex. make a REST call to /users/{id}
                    try {
                        subscriber.onNext(Arrays.asList(getMockData().get((int) id)));
                    } catch (Exception e) {
                        //Maybe we did not find the user because the ID was too high
                        subscriber.onError(new Exception("User not found"));
                        return;
                    }
                }
                subscriber.onCompleted();
            }
        });
    }

    private List<User> getMockData() {
        List<User> users = new ArrayList<>();
        for (int idx = 0; idx < 5; idx++) {
            User user = new User();
            user.id = idx;
            user.lastUpdated = new Date();
            user.status = (idx % 3 == 0) ? "ONLINE" : "OFFLINE";
            users.add(user);
        }
        return users;
    }
}
