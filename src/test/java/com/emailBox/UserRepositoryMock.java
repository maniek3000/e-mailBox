package com.emailBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryMock implements UserRepository {

    List<User> users = new ArrayList<>();

    @Override
    public User createUser(User user) {
        user.setId(1);
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}
