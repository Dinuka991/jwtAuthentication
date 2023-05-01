package com.alibou.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest request){
        AuthenticationResponse auth =  authenticationService.auth(request);
        return  new ResponseEntity<>(auth,HttpStatus.OK);
    }


}
