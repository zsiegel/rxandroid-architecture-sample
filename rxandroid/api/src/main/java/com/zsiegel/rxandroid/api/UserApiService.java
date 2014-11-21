package com.zsiegel.rxandroid.api;

import com.zsiegel.rxandroid.MockUserService;
import com.zsiegel.rxandroid.model.User;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * A service to get users from a remote API
 *
 * @author zsiegel (zac@akta.com)
 */
public class UserApiService {

    public static final int MAX_API_USERS = 10;

    public UserApiService() {
        super();
    }

    public Observable<List<User>> get(final long id) {
        return Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {

                if (id < 0) {
                    //Return all the users ex. make a rest call to /users
                    subscriber.onNext(MockUserService.getMockData(10));
                } else {
                    //Return a single user ex. make a REST call to /users/{id}
                    try {
                        subscriber.onNext(Arrays.asList(MockUserService.getMockData(MAX_API_USERS).get((int) id)));
                    } catch (Exception e) {
                        //Maybe we did not find the user
                        subscriber.onError(new Exception("User not found"));
                        return;
                    }
                }
                subscriber.onCompleted();
            }
        });
    }
}
