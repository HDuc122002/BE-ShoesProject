package com.project.ShoesProject.service;

import com.project.ShoesProject.dto.UserDTO;
import com.project.ShoesProject.entity.User;
import com.project.ShoesProject.exception.DataNotFoundException;

public interface IUserService {
    User register(UserDTO userDTO) throws DataNotFoundException, Exception;
    String login(String phoneNumber, String password) throws Exception;
}
