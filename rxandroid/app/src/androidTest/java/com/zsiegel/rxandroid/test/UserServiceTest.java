package com.zsiegel.rxandroid.test;

import android.test.AndroidTestCase;

import com.zsiegel.rxandroid.MockUserService;
import com.zsiegel.rxandroid.api.UserApiService;
import com.zsiegel.rxandroid.model.User;
import com.zsiegel.rxandroid.persistence.UserPersistenceService;
import com.zsiegel.rxandroid.request.DataRequest;

import java.util.List;

/**
 * @author zsiegel (zac@akta.com)
 */
public class UserServiceTest extends AndroidTestCase {

    UserApiService apiService;
    UserPersistenceService persistenceService;
    UserService userService;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        apiService = new UserApiService();
        persistenceService = new UserPersistenceService();
        userService = new UserService(apiService, persistenceService);
    }

    public void testRefresh() {
        DataRequest refreshRequest = new DataRequest(DataRequest.Source.REFRESH, -1);

        //Cache some users
        persistenceService.save(MockUserService.getMockData(UserPersistenceService.MAX_LOCAL_USERS));

        List<List<User>> users = userService.get(refreshRequest).toList().toBlocking().single();
        assertEquals(users.size(), 2);
        assertEquals(users.get(0).size(), UserPersistenceService.MAX_LOCAL_USERS);
        assertEquals(users.get(1).size(), UserApiService.MAX_API_USERS);
    }

    public void testGetUsersRemote() {
        DataRequest remoteRequest = new DataRequest(DataRequest.Source.REMOTE, -1);
        List<List<User>> users = userService.get(remoteRequest).toList().toBlocking().single();
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).size(), UserApiService.MAX_API_USERS);
    }

    public void testGetUsersLocal() {
        DataRequest localRequest = new DataRequest(DataRequest.Source.LOCAL, -1);

        //Cache some users
        persistenceService.save(MockUserService.getMockData(UserPersistenceService.MAX_LOCAL_USERS));

        List<List<User>> users = userService.get(localRequest).toList().toBlocking().single();
        assertEquals(users.size(), 1);
        assertEquals(users.get(0).size(), UserPersistenceService.MAX_LOCAL_USERS);
    }
}
