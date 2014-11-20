package com.zsiegel.rxandroid.api;

import dagger.Module;

@Module(complete = false, library = true)
public class ApiModule {

    private final String baseUrl;

    public ApiModule(String baseUrl) {
        super();
        this.baseUrl = baseUrl;
    }

}
