package com.emailBox;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class UserServiceTest {

    UserService userService;
    UserRepositoryMock userRepository;

    @Before
    public void setUp() {
        userRepository = new UserRepositoryMock();
        userService = new UserService(userRepository);

    }

    @Test
    public void createUser_WithCorrectValues() {
        //when
        User user = userService.createUser("user", "password");
        //then
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.getPassword()).isEqualTo("password");
    }

    @Test
    public void createUser_WithEmptyUsername(){
        //when
        Throwable result = catchThrowable(() -> userService.createUser("", "password"));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void createUser_WithBlancUsername(){
        //when
        Throwable result = catchThrowable(() -> userService.createUser("    ", "password"));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void createUser_WithNullUsername(){
        //when
        Throwable result = catchThrowable(() -> userService.createUser(null, "password"));
        //then
        assertThat(result).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void createUser_WithoutLetterOnBeginOfUsername(){
        //when
        Throwable result = catchThrowable(() -> userService.createUser("8user", "password"));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void createUser_WithLessThan4LetterUsername(){
        //when
        Throwable result = catchThrowable(() -> userService.createUser("use", "password"));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void createUser_WithDuplicateUsername(){
        //when
        User user1 = userService.createUser("user", "password");
        Throwable result = catchThrowable(() -> userService.createUser("user", "password"));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void createUser_WithoutLetterOnBeginOfPassword(){
        //when
        Throwable result = catchThrowable(() -> userService.createUser("user", "8password"));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void createUser_WithLessThan8LetterPassword(){
        //when
        Throwable result = catchThrowable(() -> userService.createUser("user", "pass"));
        //then
        assertThat(result).isExactlyInstanceOf(RuntimeException.class);
    }
}