package com.zsiegel.rxandroid.request;

/**
 * @author zsiegel (zac@akta.com)
 */
public class DataRequest {

    enum Source {
        REMOTE, //Fetch the data from the remote source
        LOCAL, //Fetch the data from the local source
        REFRESH //Fetch the local data first, then update the local data from the remote source
    }

    public long id = -1;
    public Source source;

    public DataRequest(Source source, long id) {
        super();
        this.source = source;
        this.id = id;
    }
}
