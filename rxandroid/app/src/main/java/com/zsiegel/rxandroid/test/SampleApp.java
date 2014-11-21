package com.zsiegel.rxandroid.test;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * @author zsiegel (zac@akta.com)
 */
public class SampleApp extends Application {

    private static SampleApp appContext;

    public static SampleApp get() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = (SampleApp) getApplicationContext();
        buildObjectGraph();
    }

    private void buildObjectGraph() {
        ObjectGraph objectGraph = ObjectGraph.create(Modules.modulesForApp());
        Modules.instance().setObjectGraph(objectGraph);
    }

    public void inject(Object obj) {
        Modules.instance().getObjectGraph().inject(obj);
    }
}
