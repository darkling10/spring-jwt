package com.example.learnspringjwt.auth;

import com.example.learnspringjwt.config.JwtService;
import com.example.learnspringjwt.exceptions.AuthenticationResponse;
import com.example.learnspringjwt.repositories.UserRepository;
import com.example.learnspringjwt.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.learnspringjwt.exceptions.ResponseDetails.ResponseStatusEnum.ERROR;
import static com.example.learnspringjwt.exceptions.ResponseDetails.ResponseStatusEnum.SUCCESS;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    //    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(AuthenticationService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(RegisterRequest request) {
//        final String uuid = UUID.randomUUID().toString().replace("-", "");

        var user = User.builder()
//                .id(uuid)
                .firstName((request.getFirstName()))
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        logger.info("User detected :" + user.toString());
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .responseMessage("Registration Successful")
                .responseStatus(SUCCESS)
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println("Hit");
        boolean checkAuthenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(), request.getPassword()
                        )
                )
                .isAuthenticated();

        try {
            var user = userRepository.findOneByEmail(request.getEmail())
                    .orElseThrow();


            var jwtToken = jwtService.generateToken(user);

            if (checkAuthenticate) {
                return AuthenticationResponse
                        .builder()
                        .responseMessage("Login Successful")
                        .responseStatus(SUCCESS)
                        .token(jwtToken)
                        .build();
            } else {
                return AuthenticationResponse
                        .builder()
                        .token(null)
                        .responseMessage("Login Failure")
                        .responseStatus(ERROR)
                        .build();

            }
        } catch (Exception e) {
            return AuthenticationResponse
                    .builder()
                    .token(null)
                    .responseMessage(e.getMessage())
                    .responseStatus(ERROR)
                    .build();

        }

    }

}
