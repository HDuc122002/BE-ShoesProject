package com.project.ShoesProject.controller;

import com.project.ShoesProject.dto.UserDTO;
import com.project.ShoesProject.entity.User;
import com.project.ShoesProject.exception.DataNotFoundException;
import com.project.ShoesProject.response.AuthenticationResponse;
import com.project.ShoesProject.service.Impl.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO){
        try{
            return ResponseEntity.ok(userService.login(userDTO.getPhoneNumber(), userDTO.getPassword()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
