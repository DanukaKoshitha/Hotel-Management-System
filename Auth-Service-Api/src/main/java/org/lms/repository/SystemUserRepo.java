package org.lms.repository;

import org.lms.entity.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SystemUserRepo extends JpaRepository<SystemUser,String> {
    Optional<SystemUser> findByEmail(String email);
}
