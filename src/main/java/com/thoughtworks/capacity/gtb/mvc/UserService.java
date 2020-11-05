package com.thoughtworks.capacity.gtb.mvc;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<String, User> userMap= new HashMap<>();

    public UserService() {
        userMap.put("Tom", new User(1, "Tom", "123456", "tom@qq.com"));
    }

    public void register(UserRequestDto user) {
        if (checkUserIfExisted(user.getUsername())) {
            throw new UserExistedException("用户已存在");
        }
        if (checkUsernameFormat(user.getUsername())) {
            throw new WrongFormatException("用户名不合法");
        }
        if (checkPasswordFormat(user.getPassword())) {
            throw new WrongFormatException("密码不合法");
        }
        if (!user.getEmail().isEmpty() && !checkEmailFormat(user.getEmail())) {
            throw new WrongFormatException("邮箱地址不合法");
        }
        Integer id = userMap.size() + 1;
        userMap.put(user.getUsername(), new User(id, user.getUsername(), user.getPassword(), user.getEmail()));
    }

    private boolean checkUserIfExisted(String username) {
        return userMap.containsKey(username);
    }

    private boolean checkUsernameFormat(String username) {
        return !username.matches("[a-zA-Z0-9_]{3,10}");
    }

    private boolean checkPasswordFormat(String password) {
        return !password.matches("[\\w+]{5,12}");
    }

    private boolean checkEmailFormat(String email) {
        return email.matches("^(.+)@(.+)$");
    }

    public User login(String username, String password) {
        if (checkUsernameFormat(username)) {
            throw new WrongFormatException("用户名不合法");
        }
        if (checkPasswordFormat(password)) {
            throw new WrongFormatException("密码不合法");
        }
        User user = userMap.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("用户名或密码错误");
        }
        return user;
    }
}
