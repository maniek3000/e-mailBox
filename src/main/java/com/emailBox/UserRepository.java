package com.emailBox;

import java.util.Optional;

public interface UserRepository {
    User createUser(User user);
    Optional<User> findUserByUsername(String username);


}
