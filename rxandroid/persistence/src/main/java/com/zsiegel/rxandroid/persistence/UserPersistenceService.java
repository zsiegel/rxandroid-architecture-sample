package com.zsiegel.rxandroid.persistence;

import com.zsiegel.rxandroid.MockUserService;
import com.zsiegel.rxandroid.model.User;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * A service to get users from a persistence source such as a ContentResolver
 *
 * @author zsiegel (zac@akta.com)
 */
public class UserPersistenceService {

    public static final int MAX_LOCAL_USERS = 5;

    public UserPersistenceService() {
        super();
    }

    public Observable<List<User>> get(final long id) {
        return Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {

                if (id < 0) {
                    //Return all the users in the database ex. select * from users;
                    subscriber.onNext(MockUserService.getMockData(5));
                } else {
                    //Return a single user ex. select * from user with id = {id};
                    try {
                        subscriber.onNext(Arrays.asList(MockUserService.getMockData(MAX_LOCAL_USERS).get((int) id)));
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

    public Observable<List<User>> save(final List<User> users) {
        return Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {

                //Return the users that were saved
                subscriber.onNext(users);
                subscriber.onCompleted();
            }
        });
    }
}
