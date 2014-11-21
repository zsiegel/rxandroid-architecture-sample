package com.zsiegel.rxandroid.test;

import com.zsiegel.rxandroid.api.UserApiService;
import com.zsiegel.rxandroid.persistence.UserPersistenceService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author zsiegel (zac@akta.com)
 */
@Module(complete = false, library = true, injects = {})
public class AppModule {

    public AppModule() {
        super();
    }

    @Provides
    @Singleton
    UserService providesUserService(UserApiService apiService, UserPersistenceService persistenceService) {
        return new UserService(apiService, persistenceService);
    }
}
