package com.alibou.security.auth;

import com.alibou.security.Role;
import com.alibou.security.User;
import com.alibou.security.UserRepository;
import com.alibou.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.PrimitiveIterator;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){

        User user = User.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        System.out.println(user);
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }

    public  AuthenticationResponse auth(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt).build();

    }
}
