package com.emailBox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password) {
        Pattern pattern = Pattern.compile("[A-z].*");
        Matcher matcherUsername = pattern.matcher(username);
        Matcher matcherPassword = pattern.matcher(password);
        if (username == null || username.isBlank()) {
            throw new RuntimeException("Nazwa użytkownika nie może być pusta");
        }
        if (!matcherUsername.matches()) {
            throw new RuntimeException("Nazwa użytkowniak musi się zaczynać od wielkiej lub małej litery");
        }
        if (username.length() <= 3) {
            throw new RuntimeException("Nazwa użytkownika musi zawierać conajmniej 4 znaki");
        }
        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new RuntimeException("Użytkownik o podanym loginie już istnieje");
        }
        if (password.length() < 8) {
            throw new RuntimeException("Hasło musi zawierać conajmniej 8 znaków");
        }
        if (!matcherPassword.matches()) {
            throw new RuntimeException("Hasło musi się zaczynać od wielkiej lub małej litery");
        }
        User user = new User(null, username, password);
        return userRepository.createUser(user);

    }
}
