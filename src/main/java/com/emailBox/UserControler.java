package com.emailBox;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserControler {

    private final UserService userService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserControler(UserService userService) {
        this.userService = userService;
    }

    public String createUser(String username, String password) {
        try {
            User user = userService.createUser(username, password);
            return objectMapper.writeValueAsString(user);
        } catch (Exception e) {
            return "{\"error message\": \"" + e.getMessage() + "\"}";
        }
    }
}
