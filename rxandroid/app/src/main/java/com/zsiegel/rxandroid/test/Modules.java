package com.zsiegel.rxandroid.test;

import com.zsiegel.rxandroid.api.ApiModule;
import com.zsiegel.rxandroid.persistence.PersistenceModule;

import dagger.ObjectGraph;

/**
 * @author zsiegel (zac@akta.com)
 */
public class Modules {

    private static Modules modules;

    private ObjectGraph objectGraph;

    private Modules() {
    }

    public static Modules instance() {
        if (modules == null) {
            modules = new Modules();
        }
        return modules;
    }

    public static Object[] modulesForApp() {
        return new Object[]{
                new AppModule(),
                new ApiModule("https://my.cool.endpoint"),
                new PersistenceModule(),
        };
    }

    public void setObjectGraph(ObjectGraph objectGraph) {
        this.objectGraph = objectGraph;
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }

}
