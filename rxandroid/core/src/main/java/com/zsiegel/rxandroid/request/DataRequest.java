package com.zsiegel.rxandroid.request;

/**
 * @author zsiegel (zac@akta.com)
 */
public class DataRequest {

    public enum Source {
        REMOTE, //Fetch the data from the remote source
        LOCAL, //Fetch the data from the local source
        REFRESH //Fetch the local data first, then update the local data from the remote source
    }

    public long id = -1;
    public Source source;

    /**
     * A request for data
     *
     * @param source the source of the data
     * @param id     the id of the data, if not specified all data will be returned
     */
    public DataRequest(Source source, long id) {
        super();
        this.source = source;
        this.id = id;
    }
}
