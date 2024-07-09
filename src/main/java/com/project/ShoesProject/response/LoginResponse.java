package com.project.ShoesProject.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("token")
    private String token;
}
