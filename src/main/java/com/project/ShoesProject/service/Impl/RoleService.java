package com.project.ShoesProject.service.Impl;

import com.project.ShoesProject.entity.Role;
import com.project.ShoesProject.repository.RoleRepository;
import com.project.ShoesProject.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
