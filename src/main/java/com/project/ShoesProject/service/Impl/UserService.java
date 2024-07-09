package com.project.ShoesProject.service.Impl;

import com.project.ShoesProject.dto.UserDTO;
import com.project.ShoesProject.entity.User;
import com.project.ShoesProject.exception.DataNotFoundException;
import com.project.ShoesProject.exception.InvalidParamException;
import com.project.ShoesProject.repository.UserRepository;
import com.project.ShoesProject.security.JwtTokenUtil;
import com.project.ShoesProject.security.UserDetailsServiceImpl;
import com.project.ShoesProject.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class
UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public User register(UserDTO userDTO) throws Exception {
        String phoneNumber = userDTO.getPhoneNumber();
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Phone number already exists");
        }
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .role(userDTO.getRole())
                .build();
<<<<<<< HEAD
        if (!userDTO.getPassword().equals(userDTO.getRetypePassword())) {
=======
        if (!userDTO.getPassword().equals(userDTO.getRetypePassword())){
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13
            throw new InvalidParamException("Wrong retype password");
        }
        return userRepository.save(newUser);
    }


    @Override
<<<<<<< HEAD
    public String login(String phoneNumber, String password) throws Exception {
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new DataNotFoundException("Invalid phone number / password"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        phoneNumber, password
                )
=======
    public String login(String phoneNumber, String password) throws Exception{
        User user = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new DataNotFoundException("Invalid phone number / password"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            phoneNumber,password
            )
>>>>>>> dffb0e7f4cd30063576ab2b31b7505d9bef91f13
        );
        String token = jwtTokenUtil.generateAccessToken(user);
        return token;
    }

    @Override
    public String refreshToken(UserDTO userDTO) throws Exception {
        User user = userRepository.findByPhoneNumber(userDTO.getPhoneNumber())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        String token = jwtTokenUtil.generateRefreshToken(user);
        return token;
    }
}
