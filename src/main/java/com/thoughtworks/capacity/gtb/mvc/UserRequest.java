package com.thoughtworks.capacity.gtb.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String username;

    private String password;

    private String email;
}
