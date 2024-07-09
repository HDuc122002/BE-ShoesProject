package com.project.ShoesProject.controller;

import com.project.ShoesProject.service.Impl.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/roles")
public class RoleController {
    private final RoleService roleService;
    public ResponseEntity<?> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
