package com.example.learnspringjwt.exceptions;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse extends ResponseDetails {
    private String token;

    private String responseMessage;

    private ResponseStatusEnum responseStatus;


}
