package com.project.ShoesProject.controller;

import com.project.ShoesProject.dto.UserDTO;

import com.project.ShoesProject.dto.UserLoginDTO;
import com.project.ShoesProject.entity.User;
import com.project.ShoesProject.response.LoginResponse;
import com.project.ShoesProject.service.Impl.UserService;
import com.project.ShoesProject.utils.LocalizationUtils;
import com.project.ShoesProject.utils.MessageKeys;

import com.project.ShoesProject.entity.User;
import com.project.ShoesProject.repository.UserRepository;
import com.project.ShoesProject.service.Impl.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final UserService userService;

    private final LocalizationUtils localizationUtils;

    private final UserRepository userRepository;



    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            User user = userService.register(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")

    public ResponseEntity<LoginResponse> login(@Valid @RequestBody UserLoginDTO userLoginDTO
                                              ) {
        try {
            String token = userService.login(userLoginDTO.getPhoneNumber(), userLoginDTO.getPassword());

            return ResponseEntity.ok(LoginResponse.builder()
                    .message(localizationUtils.getLocalizationMessage(MessageKeys.LOGIN_SUCCESSFULLY))
                    .token(token)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .message(localizationUtils.getLocalizationMessage(MessageKeys.LOGIN_FAILED, e.getMessage()))
                    .build());
        }

//    @PostMapping("/refreshToken")
//    public ResponseEntity<?> refreshToken(@RequestBody UserDTO userDTO){
//        try {
//            return ResponseEntity.ok(userService.refreshToken(userDTO));
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    }
}
