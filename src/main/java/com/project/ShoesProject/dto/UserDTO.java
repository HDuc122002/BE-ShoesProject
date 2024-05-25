package com.project.ShoesProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.ShoesProject.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @JsonProperty("full_name")
    @NotBlank(message = "full name is required!")
    private String fullName;
    @NotBlank(message = "phone number is required!")
    @JsonProperty("phone_number")
//    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @NotBlank(message = "Password cannot be blank")
    @JsonProperty("password")
    private String password;
    @NotBlank(message = "Retype password is required!")
    @JsonProperty("retype_password")
    private String retypePassword;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("facebook_account_id")
    private Long facebookAccountId;

    @JsonProperty("google_account_id")
    private Long googleAccountId;

    @NotNull(message = "Role is required")
    @JsonProperty("role")
    private Role role;
}
