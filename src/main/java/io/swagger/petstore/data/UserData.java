/**
 * Copyright 2018 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.swagger.petstore.data;

import io.swagger.petstore.model.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<User> users = new ArrayList<>();


    static {
        users.add(createUser(1, "user1", "first name 1", "last name 1",
                "email1@test.com", "123-456-7890", 1));
        users.add(createUser(2, "user2", "first name 2", "last name 2",
                "email2@test.com", "123-456-7890", 2));
        users.add(createUser(3, "user3", "first name 3", "last name 3",
                "email3@test.com", "123-456-7890", 3));
        users.add(createUser(4, "user4", "first name 4", "last name 4",
                "email4@test.com", "123-456-7890", 1));
        users.add(createUser(5, "user5", "first name 5", "last name 5",
                "email5@test.com", "123-456-7890", 2));
        users.add(createUser(6, "user6", "first name 6", "last name 6",
                "email6@test.com", "123-456-7890", 3));
        users.add(createUser(7, "user7", "first name 7", "last name 7",
                "email7@test.com", "123-456-7890", 1));
        users.add(createUser(8, "user8", "first name 8", "last name 8",
                "email8@test.com", "123-456-7890", 2));
        users.add(createUser(9, "user9", "first name 9", "last name 9",
                "email9@test.com", "123-456-7890", 3));
        users.add(createUser(10, "user10", "first name 10", "last name 10",
                "email10@test.com", "123-456-7890", 1));
        users.add(createUser(11, "user?10", "first name ?10", "last name ?10",
                "email101@test.com", "123-456-7890", 1));

    }

    public User findUserByName(final String username) {
        for (final User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(final User user) {
        if (users.size() > 0) {
            for (int i = users.size() - 1; i >= 0; i--) {
                if (users.get(i).getUsername().equals(user.getUsername())) {
                    users.remove(i);
                }
            }
        }
        users.add(user);
    }

    public void deleteUser(final String username) {
        users.removeIf(user -> user.getUsername().equals(username));
    }

    public static User createUser(final long id, final String username, final String firstName,
                                   final String lastName, final String email, final String phone, final int userStatus) {
        final User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword("XXXXXXXXXXX");
        user.setPhone(phone);
        user.setUserStatus(userStatus);
        return user;
    }
    @DataProvider(name = "UserData")
    public static Object[][] getUserDataProvider() {
        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }

    @DataProvider(name = "updatedUser")
    public static Object[][] getUpdatedUserDataProvider() {
        List<Object[]> updatedUsers = new ArrayList<>();

        for (User user : users) {
            User updated = new User();
            updated.setId(user.getId());
            updated.setUsername(user.getUsername());
            updated.setFirstName(user.getFirstName() + "_Updated");
            updated.setLastName(user.getLastName() + "_Updated");
            updated.setEmail("updated_" + user.getEmail());
            updated.setPhone("999-" + user.getPhone());
            updated.setPassword("NEWPASSWORD");
            updated.setUserStatus(user.getUserStatus() + 1);
            updatedUsers.add(new Object[]{user.getUsername(), updated});
        }
        return updatedUsers.toArray(new Object[0][0]);
    }
}