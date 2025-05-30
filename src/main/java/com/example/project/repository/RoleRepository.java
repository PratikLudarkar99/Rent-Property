package com.example.project.repository;
import org.springframework.stereotype.Repository;
import com.example.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Optional: find role by roleName
    Role findByRoleName(String roleName);
}
