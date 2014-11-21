package com.zsiegel.rxandroid.model;

import java.util.Date;

/**
 * A basic User model
 *
 * @author zsiegel (zac@akta.com)
 */
public class User {

    public long id;
    public String username;
    public String status;
    public Date lastUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (lastUpdated != null ? !lastUpdated.equals(user.lastUpdated) : user.lastUpdated != null)
            return false;
        if (status != null ? !status.equals(user.status) : user.status != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }
}
