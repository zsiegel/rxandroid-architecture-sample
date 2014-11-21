package com.zsiegel.rxandroid.test;

import com.zsiegel.rxandroid.api.UserApiService;
import com.zsiegel.rxandroid.model.User;
import com.zsiegel.rxandroid.persistence.UserPersistenceService;
import com.zsiegel.rxandroid.request.DataRequest;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * A service that routes a User request
 *
 * @author zsiegel (zac@akta.com)
 */
public class UserService {

    private final UserApiService apiService;
    private final UserPersistenceService persistenceService;

    public UserService(UserApiService apiService, UserPersistenceService persistenceService) {
        super();
        this.apiService = apiService;
        this.persistenceService = persistenceService;
    }

    public Observable<List<User>> get(DataRequest request) {

        //By default we go to the api for the data
        Observable<List<User>> operation;

        switch (request.source) {
            case REMOTE:
                operation = apiService.get(request.id);
                break;
            case LOCAL:
                operation = persistenceService.get(request.id);
                break;
            case REFRESH:
                //return local data first, load from the api, save that local, then finally return latest
                operation = apiService.get(request.id).flatMap(new Func1<List<User>, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> call(List<User> users) {
                        return persistenceService.save(users);
                    }
                }).startWith(persistenceService.get(request.id));
                break;
            default:
                //default to the api request
                operation = apiService.get(request.id);
                break;
        }

        return operation;
    }
}
