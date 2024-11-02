package com.ruediger.cybersecure.security_controls;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlFamilyRepository extends JpaRepository<ControlFamily, String> {
    // Additional query methods (if needed) can be defined here
}
