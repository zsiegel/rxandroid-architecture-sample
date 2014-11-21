package com.zsiegel.rxandroid.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
public class ApiModule {

    private final String baseUrl;

    public ApiModule(String baseUrl) {
        super();
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    UserApiService providesApiService() {
        return new UserApiService();
    }
}
