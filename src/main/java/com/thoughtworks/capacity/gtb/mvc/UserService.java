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

    public void register(UserRequest user) {
        if (checkUserIfExisted(user.getUsername())) {
            throw new UserExistedException("用户已存在");
        }
        Integer id = userMap.size() + 1;
        userMap.put(user.getUsername(), new User(id, user.getUsername(), user.getPassword(), user.getEmail()));
    }

    private boolean checkUserIfExisted(String username) {
        return userMap.containsKey(username);
    }

    public User login(String username, String password) {
        User user = userMap.get(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("用户名或密码错误");
        }
        return user;
    }
}
