package com.zsiegel.rxandroid.persistence;

import com.zsiegel.rxandroid.MockUserService;
import com.zsiegel.rxandroid.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.Subscriber;

/**
 * A service to get users from a local cache
 *
 * @author zsiegel (zac@akta.com)
 */
public class UserPersistenceService {

    public static final int MAX_LOCAL_USERS = 5;

    private Map<Long, User> userCache;

    public UserPersistenceService() {
        super();
    }

    public Observable<List<User>> get(final long id) {
        return Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {

                List<User> usersToReturn = new ArrayList<>();

                if (id < 0) {
                    //Return all the users in the database ex. select * from users;
                    usersToReturn.addAll(userCache.values());
                } else {
                    //Return a single user ex. select * from user with id = {id};
                    try {
                        usersToReturn = Arrays.asList(MockUserService.getMockData(MAX_LOCAL_USERS).get((int) id));
                    } catch (Exception e) {
                        //Maybe we did not find the user
                        subscriber.onError(new Exception("User not found"));
                        return;
                    }
                }

                subscriber.onNext(usersToReturn);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<User>> save(final List<User> users) {
        return Observable.create(new Observable.OnSubscribe<List<User>>() {
            @Override
            public void call(Subscriber<? super List<User>> subscriber) {
                for (User user : users) {
                    cacheUser(user.id, user);
                }
                subscriber.onNext(users);
                subscriber.onCompleted();
            }
        });
    }

    public void cacheUser(Long id, User user) {
        if (userCache == null) {
            synchronized (this) {
                if (userCache == null) {
                    userCache = new ConcurrentHashMap<>();
                }
            }
        }

        userCache.put(id, user);
    }
}
