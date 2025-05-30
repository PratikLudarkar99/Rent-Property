package com.example.project.service;

import com.example.project.model.Role;
import com.example.project.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role updateRole(Long id, Role updatedRole) {
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        existingRole.setRoleName(updatedRole.getRoleName());
        existingRole.setPermissions(updatedRole.getPermissions());

        return roleRepository.save(existingRole);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
