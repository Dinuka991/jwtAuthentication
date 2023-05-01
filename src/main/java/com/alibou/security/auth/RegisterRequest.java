package com.alibou.security.auth;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
    private  String  firstName;
    private String lastName;
    private  String email;
    private  String password;

}
